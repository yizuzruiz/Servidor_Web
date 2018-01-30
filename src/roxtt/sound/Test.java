/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.sound;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author JesúsAlberto
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
    AudioFormat format = new AudioFormat(44100.0f, 16, 1, true, true);
    TargetDataLine microphone;
    SourceDataLine speakers;
    
    try {
        microphone = AudioSystem.getTargetDataLine(format);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int numBytesRead;
        int CHUNK_SIZE = 1024;
        
    byte[] data = new byte[microphone.getBufferSize()];
    
    
    
        microphone.start();

        int bytesRead = 0;
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
        speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        speakers.open(format);
        speakers.start();
        while (bytesRead >= 0) {
            numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
            bytesRead += numBytesRead;
            // write the mic data to a stream for use later
            //out.write(data, 0, numBytesRead); 
            // write mic data to stream for immediate playback
            speakers.write(data, 0, numBytesRead);
            
        }
        speakers.drain();
        speakers.close();
        microphone.close();
    } catch (LineUnavailableException e) {
        e.printStackTrace();
    } 
    }
}

