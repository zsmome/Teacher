package com.zsmome.day09_homework_weekly_baseadapter.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片加载的工具类
 * @author xray
 *
 */
public class ImageLoader {

	/**
	 * 图片加载完成的回调接口
	 * 添加URL参数，用于做图片错位判断
	 */
	public interface OnImageLoadListener{
		//完成图片加载
		void onImageLoadComplete(String url, Bitmap bitmap);
	}
	
	private OnImageLoadListener mListener;
	
	/**
	 * 启动图片加载任务
	 * @param urlStr
	 * @param listener
	 */
	public void loadImage(String urlStr,OnImageLoadListener listener){
		this.mListener = listener;
		new ImageLoadTask().execute(urlStr);
	}
	
	/**
     * 图片加载任务
     * @author xray
     *
     */
    class ImageLoadTask extends AsyncTask<String,Void,ImageLoadTask.UrlAndBitmap>{
    	
		@Override
		protected UrlAndBitmap doInBackground(String... params) {
			
			try {
				//创建URL，指定图片地址
				URL url = new URL(params[0]);
				//打开连接获得HttpURLConnection对象
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				//获得文件输入流
				InputStream stream = conn.getInputStream();
				//把输入流转换为图片
				Bitmap bmp = BitmapFactory.decodeStream(stream);
				//关闭流
				stream.close();
				return new UrlAndBitmap(params[0],bmp);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		//包装图片地址和图片
		class UrlAndBitmap{
			String url;
			Bitmap bitmap;

			public UrlAndBitmap(String url, Bitmap bitmap) {
				this.url = url;
				this.bitmap = bitmap;
			}
		}
		
		@Override
		protected void onPostExecute(UrlAndBitmap result) {
			//进行接口回调
			if(mListener != null){
				mListener.onImageLoadComplete(result.url,result.bitmap);
			}
		}
    	
    }
}
