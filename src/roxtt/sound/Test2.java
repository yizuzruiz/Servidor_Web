/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roxtt.sound;

/**
 *
 * @author JesúsAlberto
 */
public class Test2 implements NeetJavaSound.NjsCallback
{
    public static void main(String[] args)
    {
        // sampleRate, channels, wantedLatency in ms
        NeetJavaSound.open(44100, 2, 30);
        NeetJavaSound.setCallback(new Test2());
        NeetJavaSound.start();

        // waste some time

        NeetJavaSound.close();
    }

    private int phase = 0;
    private int step = (int)((16777216 * 220.0) / 44100.0);

    // Just a proof-of-concept so: non-bandlimited.
    @Override
    public void render(final float[] output, final int nframes)
    {
        for(int i = 0; i < nframes; i++)
        {
            this.phase = (this.phase + this.step) & 0xffffff;
            final float o = ((this.phase >> 12) / 2048.0f - 1.0f) * 0.25f;
            output[i * 2] = o;
            output[i * 2 + 1] = o;
        }
    }
}