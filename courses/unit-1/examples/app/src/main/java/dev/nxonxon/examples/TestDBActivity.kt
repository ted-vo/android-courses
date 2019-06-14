package dev.nxonxon.examples

import android.Manifest
import android.annotation.TargetApi
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.nxonxon.examples.database.StudentsContentProvider
import dev.nxonxon.examples.database.StudentsContentProvider.Companion.GRADE
import dev.nxonxon.examples.database.StudentsContentProvider.Companion.NAME
import kotlinx.android.synthetic.main.activity_test_db.*

class TestDBActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_db)

        val s = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        requestPermissionsSafely(s, 1)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        val students = Uri.parse(StudentsContentProvider.URL)
        return CursorLoader(
            this,
            students,
            null,
            null,
            null,
            "name"
        )
    }

    override fun onLoadFinished(p0: Loader<Cursor>, p1: Cursor?) {
        Log.d("INFO", "This is working")
        while (p1?.moveToNext() == true) {
            Log.i("Student id : ", p1.getString(p1.getColumnIndex("_id")))
            Log.i("Student name : ", p1.getString(p1.getColumnIndex("name")))
            Log.i("Student grade: ", p1.getString(p1.getColumnIndex("grade")))
        }
        supportLoaderManager.destroyLoader(1)
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {}

    fun onClickAddStudent(view: View) {
        // Add a new student record
        val values = ContentValues()
        values.put(NAME, edtName.text.toString())
        values.put(GRADE, edtGrade.text.toString())

        val uri = contentResolver.insert(Uri.parse(StudentsContentProvider.URL), values)
        Toast.makeText(baseContext, uri?.toString(), Toast.LENGTH_LONG).show()
    }

    fun onClickRetrieveStudents(view: View) {
        // Retrieve student records
        supportLoaderManager.initLoader(1, null, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        return when (requestCode) {
            1 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        baseContext,
                        "Please gant permisstion for write data to DB",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun hasPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (!hasPermission(permission)) return false
        }
        return true
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun hasPermission(permission: String) =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}