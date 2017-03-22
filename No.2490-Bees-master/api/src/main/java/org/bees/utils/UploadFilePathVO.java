package org.bees.utils;

public class UploadFilePathVO{
	 public String realPath;
	    public String relativePath;	    
	    private int imgHeight; // 上传图片的高
	    private int imgWidth; // 宽
		/**
		 * @return the realPath
		 */
		public String getRealPath() {
			return realPath;
		}
		/**
		 * @param realPath the realPath to set
		 */
		public void setRealPath(String realPath) {
			this.realPath = realPath;
		}
		/**
		 * @return the relativePath
		 */
		public String getRelativePath() {
			return relativePath;
		}
		/**
		 * @param relativePath the relativePath to set
		 */
		public void setRelativePath(String relativePath) {
			this.relativePath = relativePath;
		}
		/**
		 * @return the imgHeight
		 */
		public int getImgHeight() {
			return imgHeight;
		}
		/**
		 * @param imgHeight the imgHeight to set
		 */
		public void setImgHeight(int imgHeight) {
			this.imgHeight = imgHeight;
		}
		/**
		 * @return the imgWidth
		 */
		public int getImgWidth() {
			return imgWidth;
		}
		/**
		 * @param imgWidth the imgWidth to set
		 */
		public void setImgWidth(int imgWidth) {
			this.imgWidth = imgWidth;
		}
	    
}