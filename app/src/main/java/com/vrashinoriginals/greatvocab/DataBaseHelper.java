package com.vrashinoriginals.greatvocab;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by vrashinaggarwal on 8/3/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.vrashinoriginals.greatvocab/databases/";

    private static String DB_NAME = "words";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DataBaseHelper(Context context) throws IOException {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.createDataBase();
    }

    /*public DataBaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }*/
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
            Log.d("logging","db exist");
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            Log.d("logging","db not exist");
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        Log.d("logging","inside copy database");
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            createDataBase();
            /*db.execSQL(Constants.CREATE_TB);
            Log.d("logging","inside insert data");
            db.execSQL("DELETE FROM "+Constants.TB_NAME);
            //String query = "Insert into d_WORDS_MASTER( WORD, MEANING, SENTENCE, SCUT, B3, B8, M1, FAV ) VALUES ('aberration ','straying away from what is normal ','they described the outbreak of violence in the area as an aberration ','ant & bear sounds strange together ',1,1,0,0), ('abhor ','to hate  to detest ',' ',' ',1,1,0,0), ('abide ','be faithful  to endure ',' ',' ',1,1,0,0), ('abscond ','to go away suddenly ',' ',' ',1,1,0,0), ('abstruse ','difficult to comprehend  obscure ',' ',' ',1,1,0,0), ('abysmal ','extreme bad ',' ',' ',1,1,0,0), ('acclaimed ','welcomed with shouts and approval ',' ',' ',1,1,0,0), ('accolade ','praise  approval ',' ',' ',1,1,0,0), ('acumen ','Keen, quick, accurate insight or judgment ',' ',' ',1,1,0,0), ('adorn ','add beauty  decorate ',' ',' ',1,1,0,0), ('adulteration ','making unpure  poorer in quality ',' ',' ',1,1,0,0), ('affable ','polite and friendly ',' ',' ',1,1,0,0), ('affinity ','close connection  relationship ',' ',' ',1,1,0,0), ('aggravate ','make worse  irritate ',' ',' ',1,1,0,0), ('agile ','active  quick moving ',' ',' ',1,1,0,0), ('ail ','to cause pain, uneasiness, or trouble to. ',' ',' ',1,1,0,0), ('allegiance ','duty support loyalty ',' ',' ',1,1,0,0), ('alleviate ','make pain easier to bear ',' ',' ',1,1,0,0), ('alloy ','to debase by mixing with something inferior ',' ',' ',1,1,0,0), ('amalgamate ','mix  combine  unite societies ',' ',' ',1,1,0,0), ('ambidextrous ','able to use the left hand or the right equally well ',' ',' ',1,1,0,0), ('ambiguous ','doubtful  uncertain ',' ',' ',1,1,0,0), ('ambivalent ','having both of two contrary meanings ',' ',' ',1,1,0,0), ('ameliorate ','improve  make better ',' ',' ',1,1,0,0), ('anguish ','severe suffering ',' ',' ',1,1,0,0), ('animosity ','strong dislike ',' ',' ',1,1,0,0), ('antidote ','medicine used against a poison or a disease ',' ',' ',1,1,0,0), ('apartheid ','brutal racial discrimination ',' ',' ',1,1,0,0)";
            String query = "Insert into d_WORDS_MASTER( WORD, MEANING, SENTENCE, SCUT, B3, B8, M1, FAV ) VALUES ('aberration ','straying away from what is normal ','they described the outbreak of violence in the area as an aberration ','ant & bear sounds strange together ',1,1,0,0), ('abhor ','to hate  to detest ',null,null,1,1,0,0), ('abide ','be faithful  to endure ',null,null,1,1,0,0), ('abscond ','to go away suddenly ',null,null,1,1,0,0), ('abstruse ','difficult to comprehend  obscure ',null,null,1,1,0,0), ('abysmal ','extreme bad ',null,null,1,1,0,0), ('acclaimed ','welcomed with shouts and approval ',null,null,1,1,0,0), ('accolade ','praise  approval ',null,null,1,1,0,0), ('acumen ','Keen, quick, accurate insight or judgment ',null,null,1,1,0,0), ('adorn ','add beauty  decorate ',null,null,1,1,0,0), ('adulteration ','making unpure  poorer in quality ',null,null,1,1,0,0), ('affable ','polite and friendly ',null,null,1,1,0,0), ('affinity ','close connection  relationship ',null,null,1,1,0,0), ('aggravate ','make worse  irritate ',null,null,1,1,0,0), ('agile ','active  quick moving ',null,null,1,1,0,0), ('ail ','to cause pain, uneasiness, or trouble to. ',null,null,1,1,0,0), ('allegiance ','duty support loyalty ',null,null,1,1,0,0), ('alleviate ','make pain easier to bear ',null,null,1,1,0,0), ('alloy ','to debase by mixing with something inferior ',null,null,1,1,0,0), ('amalgamate ','mix  combine  unite societies ',null,null,1,1,0,0), ('ambidextrous ','able to use the left hand or the right equally well ',null,null,1,1,0,0), ('ambiguous ','doubtful  uncertain ',null,null,1,1,0,0), ('ambivalent ','having both of two contrary meanings ',null,null,1,1,0,0), ('ameliorate ','improve  make better ',null,null,1,1,0,0), ('anguish ','severe suffering ',null,null,1,1,0,0), ('animosity ','strong dislike ',null,null,1,1,0,0), ('antidote ','medicine used against a poison or a disease ',null,null,1,1,0,0), ('apartheid ','brutal racial discrimination ',null,null,1,1,0,0)";
            db.execSQL(query);*/

        }catch (Exception ex)
        {
            ex.printStackTrace();
            Log.d("logging","db error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TB_NAME);
        onCreate(db);
    }
    
    public void insertData(){


    }
}
