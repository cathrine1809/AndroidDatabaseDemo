package edu.unikl.stud0104.androiddatabasedemo.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import edu.unikl.stud0104.androiddatabasedemo.entity.Student;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static String DATABASE_NAME = "testdb";
	private static int DATABASE_VERSION =1;
	
	public DatabaseHelper (Context context, String name, CursorFactory factory,int version){
		super (context, name, factory, version);	
	}
	public DatabaseHelper(Context context){
		super( context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override 
	public void onCreate (SQLiteDatabase database){
		
		String sql=
				"CREATE TABLE students (id TEXT PRIMARY KEY, name TEXT, sem INTEGER, course TEXT)";
		database.execSQL(sql);
		
		sql = "INSERT INTO students VALUES ('123456','Cathrine', '3', 'BSE')";
		database.execSQL(sql);
		
		sql = "INSERT INTO students VALUES ('123457','Izzat', '3', 'BNS')";
		database.execSQL(sql);
		
		sql = "INSERT INTO students VALUES ('123458','Afiq', '5', 'BSE')";
		database.execSQL(sql);
		
		sql = "INSERT INTO students VALUES ('123459','Zoey', '8', 'BCSS')";
		database.execSQL(sql);
		
		sql = "INSERT INTO students VALUES ('123489','Juliana', '3', 'BSE')";
		database.execSQL(sql);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
			
	}
	
	public List<Student> getStudentList(){
		List<Student> studentsList = new ArrayList<Student>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		String sqlQuery ="SELECT * FROM students";
		Cursor cursor = db.rawQuery(sqlQuery, null);
		while (!cursor.isLast()){
			cursor.moveToNext();
			Student student = new Student ();
			student.setId(cursor.getString(0));
			student.setName(cursor.getString(1));
			student.setSamester(cursor.getInt(2));
			student.setCourse(cursor.getString(3));
			studentsList.add(student);
		}
		cursor.close();
		db.close();
		return studentsList;
		
	}
	
}
