import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MessagePanel {
    ListView MessageView;
    EditText MessageTxt;
    ImageButton BtnSendTxt;
    ImageButton BtnSendImage;
    ImageButton BtnSendFile;

    public MessagePanel(ListView MessageView,
                        final EditText MessageTxt,
                        ImageButton BtnSendTxt,
                        ImageButton BtnSendImage,
                        ImageButton BtnSendFile) {

        this.MessageView = MessageView;
        this.MessageTxt = MessageTxt;
        this.BtnSendTxt = BtnSendTxt;
        this.BtnSendImage = BtnSendImage;
        this.BtnSendFile = BtnSendFile;

        this.BtnSendTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.BtnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.BtnSendFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void putText(String name, String text) {

    }

    public void putImage() {

    }

    public void putTimestamp() {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(System.currentTimeMillis()));
    }


}
