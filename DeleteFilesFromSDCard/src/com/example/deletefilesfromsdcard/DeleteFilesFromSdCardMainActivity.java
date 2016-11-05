package com.example.deletefilesfromsdcard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DeleteFilesFromSdCardMainActivity extends Activity {
	
	String key = "!/)(23abcdLC#"; // needs to be at least 8 characters for DES
	
	private Button deleteSdCardFile, deleteSdCardDirectory, deleteDirectoryCommand, encryptTheFiles, decryptTheFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_files_from_sd_card_main);
        
        deleteSdCardFile=(Button)findViewById(R.id.button1);
        deleteSdCardFile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File dir = Environment.getExternalStorageDirectory();
				File deleteFile = new File(dir, "/Download/linux commands.txt");
				
				boolean deleteFileStatus = deleteFile.delete();
				
				if(deleteFileStatus){
					Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Delete Failed", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
        deleteSdCardDirectory=(Button)findViewById(R.id.button2);
        deleteSdCardDirectory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				boolean deleteFileStatus=false;
				
				File dir = Environment.getExternalStorageDirectory();
				
				File deleteDir = new File(dir+ "/DummY");
				if (deleteDir.isDirectory()) {
			        String[] children = deleteDir.list();
			        for (int i = 0; i < children.length; i++) {
			        	
			        	File childDirectory=new File(deleteDir, children[i]);
			        	if(childDirectory.isDirectory()){
			        		String childDirectoryChildrens[]=childDirectory.list();
			        		for(int j=0;j<childDirectoryChildrens.length;j++){
			        			deleteFileStatus=new File(childDirectory, childDirectoryChildrens[i]).delete();
			        		}
			        	}else{
			        		deleteFileStatus=childDirectory.delete();
			        	}
			        }
			    }else{
			    	
			    	deleteFileStatus=deleteDir.delete();
			    	
			    	/*if(deleteDirStatus){
						Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Delete Failed", Toast.LENGTH_LONG).show();
					}*/
			    }
				
				/*if(deleteDir.getTotalSpace()<=0){
					Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Delete Failed", Toast.LENGTH_LONG).show();
				}*/
				
			}
		});
        
        deleteDirectoryCommand=(Button)findViewById(R.id.button3);
        deleteDirectoryCommand.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
					Process process=Runtime.getRuntime().exec("rm -r DummY");
					process.waitFor();
					
					long totalMemory=Runtime.getRuntime().totalMemory();
					
					if(totalMemory>=1024){
						float sizeInMB=(float)totalMemory/1024;
						if(sizeInMB>=1024){
							float sizeInGB=(float)sizeInMB/1024;
							System.out.println("totalMemory in bytes"+totalMemory+"\n Total Memory in MB"+sizeInMB+"\n Total Memory in GB"+sizeInGB);
						}
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        
        encryptTheFiles=(Button)findViewById(R.id.button4);
        encryptTheFiles.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File dir = Environment.getExternalStorageDirectory();
				File fileEncryptDirectory = new File(dir+"/SecurityFiles");
				
				if(fileEncryptDirectory.isDirectory()){
					String[] encryptFilesList=fileEncryptDirectory.list();
					for(int i=0;i<encryptFilesList.length;i++){
						
						try {

							FileInputStream fis = new FileInputStream(fileEncryptDirectory.getPath()+"/"+encryptFilesList[i]);
							FileOutputStream fos = new FileOutputStream(fileEncryptDirectory.getPath()+"/encrypted_"+encryptFilesList[i]);
							encrypt(key, fis, fos);
							
						} catch (Throwable e) {
							e.printStackTrace();
						}
						
					}
				}
				/*try{
				
					FileInputStream fis = new FileInputStream(fileEncryptDirectory.getPath()+"/doctors_info_96.png");
					FileOutputStream fos = new FileOutputStream(fileEncryptDirectory.getPath()+"/encrypted_DI.png");
					encrypt(key, fis, fos);
				} catch (Throwable e) {
					e.printStackTrace();
				}*/
				
			}
		});
        
        
        decryptTheFiles=(Button)findViewById(R.id.button5);
        decryptTheFiles.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File dir = Environment.getExternalStorageDirectory();
				File fileDecryptDirectory = new File(dir+"/SecurityFiles");
				
				if(fileDecryptDirectory.isDirectory()){
					String[] decryptFilesList=fileDecryptDirectory.list();
					for(int i=0;i<decryptFilesList.length;i++){
						
						try {

							FileInputStream fis2 = new FileInputStream(fileDecryptDirectory.getPath()+"/encrypted_"+decryptFilesList[i]);
							FileOutputStream fos2 = new FileOutputStream(fileDecryptDirectory.getPath()+"/decrypted_"+decryptFilesList[i]);
							decrypt(key, fis2, fos2);
							
						} catch (Throwable e) {
							e.printStackTrace();
						}
						
					}
				}
				
				/*try {

					FileInputStream fis2 = new FileInputStream(fileDecryptDirectory.getPath()+"/encrypted_DI.png");
					FileOutputStream fos2 = new FileOutputStream(fileDecryptDirectory.getPath()+"/decrypted_DI.png");
					decrypt(key, fis2, fos2);
					
				} catch (Throwable e) {
					e.printStackTrace();
				}*/
				
				
			}
		});
        
    }
    
    
    public void deleteFiles(){
    	
    	boolean deleteFileStatus=false;
    	
    	File dir = Environment.getExternalStorageDirectory();
		
		File deleteDir = new File(dir+ "/SecurityFiles");
		if (deleteDir.isDirectory()) {
	        String[] children = deleteDir.list();
	        for (int i = 0; i < children.length; i++) {
	        	
	        	File childDirectory=new File(deleteDir, children[i]);
	        	if(childDirectory.isDirectory()){
	        		String childDirectoryChildrens[]=childDirectory.list();
	        		for(int j=0;j<childDirectoryChildrens.length;j++){
	        			deleteFileStatus=new File(childDirectory, childDirectoryChildrens[i]).delete();
	        		}
	        	}else{
	        		deleteFileStatus=childDirectory.delete();
	        	}
	        }
	    }else{
	    	
	    	deleteFileStatus=deleteDir.delete();
	    	
	    	/*if(deleteDirStatus){
				Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(DeleteFilesFromSdCardMainActivity.this, "Delete Failed", Toast.LENGTH_LONG).show();
			}*/
	    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_delete_files_from_sd_card_main, menu);
        return true;
    }
    
    public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	
	public String getFileExtenstionName(String fileName){
		
		  int mid= fileName.lastIndexOf(".");
		  String fname=fileName.substring(0,mid);
		  String ext=fileName.substring(mid+1,fileName.length());  
		  System.out.println("File name ="+fname);
		  System.out.println("Extension ="+ext);  
		  
		  return ext;
	}
    
}
