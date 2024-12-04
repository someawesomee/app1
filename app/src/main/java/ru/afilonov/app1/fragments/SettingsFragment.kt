package ru.afilonov.app1.fragments

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.afilonov.app1.R
import ru.afilonov.app1.databinding.FragmentSettingsBinding
import java.io.File
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding ?: throw Exception()


    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    private val NOTIFICATIONS_KEY = booleanPreferencesKey("notifications")

    private val fileName = "file_12.txt"
    private val backupName = "backup_file_12.txt"

    private val sharedPrefs by lazy {
        requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDataStoreSettings()
        loadEmail()
        updateFileStatus()

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveThemeSetting(isChecked)
        }

        binding.notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveNotificationsSetting(isChecked)
        }

        binding.emailInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                saveEmail(binding.emailInput.text.toString())
            }
        }

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_signInFragment)
        }

        if(isFileExists())
            binding.btnDel.visibility = View.VISIBLE
        else
            binding.btnDel.visibility = View.INVISIBLE

        if (isBackupExists())
            binding.btnBackup.visibility = View.VISIBLE
        else
            binding.btnBackup.visibility = View.INVISIBLE

        binding.btnDel.setOnClickListener {
            if (backupFileToInternalStorage()) {
                if (deleteExternalFile()) {
                    updateFileStatus()
                    binding.btnDel.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "File deleted and backed up", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to delete file", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Backup failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackup.setOnClickListener {
            if (restoreFileFromInternalStorage()) {
                updateFileStatus()
                Toast.makeText(requireContext(), "File restored", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed to restore file", Toast.LENGTH_SHORT).show()
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    Data Store
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun saveThemeSetting(isDarkTheme: Boolean) {
        lifecycleScope.launch {
            requireContext().dataStore.edit { preferences ->
                preferences[DARK_THEME_KEY] = isDarkTheme
            }
        }
    }

    private fun saveNotificationsSetting(isEnabled: Boolean) {
        lifecycleScope.launch {
            requireContext().dataStore.edit { preferences ->
                preferences[NOTIFICATIONS_KEY] = isEnabled
            }
        }
    }

    private fun loadDataStoreSettings() {
        lifecycleScope.launch {
            requireContext().dataStore.data.collect { preferences ->
                val isDarkTheme = preferences[DARK_THEME_KEY] ?: false
                val areNotificationsEnabled = preferences[NOTIFICATIONS_KEY] ?: true

                view?.findViewById<Switch>(R.id.theme_switch)?.isChecked = isDarkTheme
                view?.findViewById<Switch>(R.id.notification_switch)?.isChecked =
                    areNotificationsEnabled
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    Shared Preference
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun saveEmail(email: String) {
        sharedPrefs.edit().putString("user_email", email).apply()
    }

    private fun loadEmail() {
        val email = sharedPrefs.getString("user_email", "") ?: ""
        binding.emailInput.setText(email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    Save to txt
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun isFileExists(): Boolean {
        val externalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(externalDir, fileName)
        return file.exists()
    }

    private fun isBackupExists(): Boolean {
        val internalFile = File(requireContext().filesDir, backupName)
        return internalFile.exists()
    }

    private fun deleteExternalFile(): Boolean {
        val externalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(externalDir, fileName)
        return file.delete()
    }

    private fun backupFileToInternalStorage(): Boolean {
        val externalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(externalDir, fileName)
        if (file.exists()) {
            val internalFile = File(requireContext().filesDir, backupName)
            return try {
                file.copyTo(internalFile, overwrite = true)
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
        return false
    }

    private fun restoreFileFromInternalStorage(): Boolean {
        val internalFile = File(requireContext().filesDir, backupName)
        val externalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val restoredFile = File(externalDir, fileName)

        if (internalFile.exists()) {
            return try {
                internalFile.copyTo(restoredFile, overwrite = true)
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
        return false
    }

    private fun updateFileStatus() {
        if (isFileExists()) {
            binding.fileStatus.text = getString(R.string.file_status_exists)
            binding.btnDel.visibility = View.VISIBLE
        } else {
            binding.fileStatus.text = getString(R.string.file_status_not_found)
            binding.btnDel.visibility = View.INVISIBLE
        }

        if (isBackupExists()) {
            binding.backupStatus.text = getString(R.string.backup_status_exists)
            binding.btnBackup.visibility = View.VISIBLE
        } else {
            binding.backupStatus.text = getString(R.string.backup_status_not_found)
            binding.btnBackup.visibility = View.INVISIBLE
        }
    }
}