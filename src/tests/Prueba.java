/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;
import au.edu.jcu.v4l4j.FrameGrabber;
import au.edu.jcu.v4l4j.ImageFormat;
import au.edu.jcu.v4l4j.VideoDevice;
import au.edu.jcu.v4l4j.examples.ImageProcessor;
import au.edu.jcu.v4l4j.examples.VideoViewer;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.planewalk.apollon.jlinbar.JLinBar;
/**
 *
 * @author JesúsAlberto
 */
public class Prueba  implements ImageProcessor {
private int width;
  private int height;
  private int std;
  private int channel;
  private VideoDevice vd;
  private VideoViewer viewer;

  public Prueba(VideoDevice v, int w, int h, int s, int c)
    throws V4L4JException
  {
    this.viewer = new VideoViewer(v, this);
    this.vd = v;
    List fmts;
    
    if (!this.vd.supportRGBConversion()) {
      String msg = "Image from this video device cannot be converted\nto RGB. If no other application is currently using the\ndevice, please submit a bug report about this issue,\nso support for your device can be added to v4l4j.\nSee README file in the v4l4j/ directory for directions.";

      JOptionPane.showMessageDialog(null, msg);
      fmts = new Vector();
    } else {
      fmts = this.vd.getDeviceInfo().getFormatList().getRGBEncodableFormats();
    }
    this.width = w;
    this.height = h;
    this.std = s;
    this.channel = c;

    this.viewer.initGUI(fmts.toArray(), w, h, "RGB");
  }

  public FrameGrabber getGrabber(ImageFormat i)
    throws V4L4JException
  {
    return this.vd.getRGBFrameGrabber(this.width, this.height, this.channel, this.std, i);
  }

  public void releaseGrabber()
  {
    this.vd.releaseFrameGrabber();
  }

  public void processImage(byte[] b)
  {
    this.viewer.setImageRaster(b);
  }

  public static void main(String[] args)
    throws V4L4JException, IOException
  {
    String dev;
    try
    {
      dev = args[0];
    }
    catch (Exception e)
    {
      
      dev = "/dev/video0";
    }int w;
    try { w = Integer.parseInt(args[1]);
    }
    catch (Exception e)
    {
     
      w = 640;
    }int h;
    try { h = Integer.parseInt(args[2]);
    }
    catch (Exception e)
    {
      
      h = 480;
    }int std;
    try { std = Integer.parseInt(args[3]);
    }
    catch (Exception e)
    {
      
      std = 0;
    }int channel;
    try { channel = Integer.parseInt(args[4]);
    }
    catch (Exception e)
    {
      
      channel = 0;
    }

    System.out.println("dev=" + dev + " w=" + w + "h= " + h);

    new JLinBar(new VideoDevice(dev), w, h, std, channel);
  }
    
}
