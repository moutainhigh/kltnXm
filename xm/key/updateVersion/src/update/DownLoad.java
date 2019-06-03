package update;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoad {
    JTextField mTextArea = new JTextField();

    public int dkb=0;
    public int dmb=0;
    public int dgb=0;


    public DownLoad() {
        JFrame frame = new JFrame("�汾����");// ��ܲ���
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowsWidth=500;
        int windowsHeight=300;
        frame.setBounds((width - windowsWidth) / 2,(height - windowsHeight) / 2, windowsWidth, windowsHeight);
        mTextArea.setBounds(0,0,windowsWidth,windowsHeight);
        frame.add(mTextArea);
        mTextArea.setText("������N�ֽ�");
        mTextArea.setFont(new Font("����",Font.BOLD,16));
        mTextArea.setHorizontalAlignment(JTextField.CENTER);
        frame.setVisible(true);
    }

    public  ByteArrayOutputStream getByteArrayOutputStream(String fieURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // ����URL
            URL url = new URL(fieURL);
            byte[] by = new byte[1024];
            // ��������
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // �����ݶ�ȡ�ڴ���
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
                dkb=dkb+1;
                if(dkb==1024){
                    dmb=dmb+1;
                    dkb=0;
                    if(dmb==1024){
                        dgb=1;
                        dmb=0;
                    }
                }
                String sa ="";
                if(dgb!=0)
                    sa="������"+dgb+"GB "+dmb+"MB "+dkb+"KB";
                else if(dmb!=0)
                    sa="������"+dmb+"MB "+dkb+"KB";
                else
                    sa="������"+dkb+"KB";
                mTextArea.setText(sa);

            }
            // �ر���
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }




}
