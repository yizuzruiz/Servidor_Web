/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author JesúsAlberto
 */
public class Sound {
    
    AudioFormat format = new AudioFormat(8000.0F, 16, 1, true, false);
    SourceDataLine sourceDataLine;
    TargetDataLine targetDataLine;

    public Sound()
    {
        initSourceDataLine();
        initTargetDataLine();
    }

    private void initSourceDataLine()
    {
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);

        if(!AudioSystem.isLineSupported(dataLineInfo))
        {
            System.err.println("Can't play audio!");
            return;
        }

        try
        {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(format);
        }
        catch(LineUnavailableException e)
        {
            System.err.println("Can't play audio!");
            return;
        }

        sourceDataLine.start();
        System.out.println("Playing audio...");
    }

    private void initTargetDataLine()
    {
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);

        if(!AudioSystem.isLineSupported(dataLineInfo))
        {
            System.err.println("Can't record audio!");
            return;
        }

        try
        {
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(format);
        }
        catch(LineUnavailableException e)
        {
            System.err.println("Can't record audio!");
            return;
        }

        targetDataLine.start();
        System.out.println("Recording audio...");
    }

    byte[] read()
    {
        byte[] data = new byte[targetDataLine.getBufferSize() / 5];
        targetDataLine.read(data, 0, data.length);
        write(data);//temporary, to test if the correct data is red
        return data;
    }

    void write(byte[] data)
    {
        sourceDataLine.write(data, 0, data.length);
    }
}
