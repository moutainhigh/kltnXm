package update;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public  class Update {

public static final String sdkfile="c://";
    public static void updateVerion(){
//        String path = OPen();
        DownLoad mDownLoad =new DownLoad();
        ByteArrayOutputStream mByteArrayOutputStream =mDownLoad.getByteArrayOutputStream("http://"+UPdate_Main.BaseUrl+"/upload/file/"+UPdate_Main.S_FILE_NAME);
        try {
            newFile(mByteArrayOutputStream,sdkfile+UPdate_Main.S_FILE_NAME);
            JOptionPane.showMessageDialog( null, "������ɣ���ʹ���µİ汾", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            UPdate_Main.writeFile(newVersion);
            Runtime.getRuntime().exec("java -Xms256m -Xmx512m -jar "+sdkfile+UPdate_Main.S_FILE_NAME);
        }catch (Exception e){
            JOptionPane.showMessageDialog( null, "�����쳣", "��ʾ", JOptionPane.ERROR_MESSAGE);
        }

        System.exit(0);

    }
    private static void newVerion(){
        int ikey =  JOptionPane.showConfirmDialog(null, "���°汾���Ƿ���£�",
                "��ʾ", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(ikey==JOptionPane.YES_OPTION){
            updateVerion();
        }
    }
    public static String newVersion="";
    public static void InitVerion(){
        try {
              newVersion =  HttpRequest.sendPost("http://"+UPdate_Main.BaseUrl+"/version",null);
            File mfile = new File (sdkfile+UPdate_Main.S_FILE_NAME);
            if(!mfile.isFile()){
                newVerion();
            }else{
                if(!newVersion.equals(UPdate_Main.version_nulber)){
                    newVerion();
                }
            }

            Runtime.getRuntime().exec("java -Xms256m -Xmx512m -jar "+sdkfile+UPdate_Main.S_FILE_NAME);
        }catch (Exception e){
            JOptionPane.showMessageDialog( null, "�����쳣������", "����", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }


    }

    /**
     * ����byte���飬�����ļ�
     */
    public static void newFile(ByteArrayOutputStream baos , String fileName) throws Exception{
        FileOutputStream mfile = new FileOutputStream(new File(fileName));
        baos.writeTo(mfile);
        baos.flush();
        baos.close();
    }

    private static String OPen(){
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "ѡ���°汾���·��");
        File file=jfc.getSelectedFile();
        if(file.isDirectory()){
            return  file.toString();
        }else {
            JOptionPane.showMessageDialog( null, "����ֻ��ѡ���ļ���", "����", JOptionPane.ERROR_MESSAGE);
            return  OPen();
        }
    }



}
