package ImageProcess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImgFunction {
	static File file;
	static String FileAdd;

	public static void main(String args[]) throws IOException {
	}

	public static BufferedImage Laplacian(File f, int i) throws IOException {
		// TODO Auto-generated method stub

		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		// 為了在修改Pixel值後不影響計算，修改後Pixel值另外儲存
		BufferedImage img2 = ImageIO.read(f);
		int pixel[][] = new int[img.getWidth()][img.getHeight()];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		if(i==0) {
			int arr[][] = { { 0 , 1 , 0 }, { 1 , -4 , 1 }, { 0 , 1 , 0 } };
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					for (int a = -1; a <= 1; a++) {
						for (int b = -1; b <= 1; b++) {
							if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
								pixel[x][y] += (img.getRGB(x + a, y + b) & 0x000000ff) * arr[b + 1][a + 1];
								
							}
						}
					}
					if (pixel[x][y]>max) {
						max = pixel[x][y];
					}
					if (pixel[x][y]<min) {
						min = pixel[x][y];
					}
				}
			}
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
	
					int n = (int) ((pixel[x][y] - min) / (double) (max - min) * 255);
					n = n<<16 | n<<8 | n;
					img2.setRGB(x, y, n);
				}
			}
		}else if(i==1) {
			int arr[][] = { { 1 , 1 , 1 }, { 1 , -8 , 1 }, { 1 , 1 , 1 } };
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					for (int a = -1; a <= 1; a++) {
						for (int b = -1; b <= 1; b++) {
							if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
								pixel[x][y] += (img.getRGB(x + a, y + b) & 0x000000ff) * arr[b + 1][a + 1];
								
							}
						}
					}
					if (pixel[x][y]>max) {
						max = pixel[x][y];
					}
					if (pixel[x][y]<min) {
						min = pixel[x][y];
					}
				}
			}
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
	
					int n = (int) ((pixel[x][y] - min) / (double) (max - min) * 255);
					n = n<<16 | n<<8 | n;
					img2.setRGB(x, y, n);
				}
			}
		}
		return img2;
		
	}

	public static BufferedImage Gamma(File f,double d) throws IOException {
		// TODO 以 d 作為 Gamma 值計算修改後 Pixel值

		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				// 讀取圖片座標(x,y)的RGB值
				int pixel = img.getRGB(x, y);

				// 計算RGB各值
				int red = (int) (Math.pow(((pixel & 0xff0000) >> 16) / 255.0, d) * 255);
				int green = (int) (Math.pow(((pixel & 0x00ff00) >> 8) / 255.0, d) * 255);
				int blue = (int) (Math.pow((pixel & 0x0000ff) / 255.0, d) * 255);
				pixel = (red << 16) | (green << 8) | blue;
				// 改變Pixel值
				img.setRGB(x, y, pixel);
			}
		}
		return img;
	}

	public static BufferedImage Sobel(File f, int i) throws IOException {
		// TODO 根據模式 j 進行兩種不同操作

		// 為了修改 Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		
		int dy[][] = new int[img.getWidth()][img.getHeight()];
		int dx[][] = new int[img.getWidth()][img.getHeight()];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		if(i==1) {
			int arr1[][] = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					for (int a = -1; a <= 1; a++) {
						for (int b = -1; b <= 1; b++) {
							if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
								dy[x][y] += (img.getRGB(x + a, y + b) & 0x000000ff) * arr1[b + 1][a + 1];
								// System.out.println(pixel[x][y]);
							}
						}
					}
					if (dy[x][y] > max) {
						max = dy[x][y];
					}
					if (dy[x][y] < min) {
						min = dy[x][y];
					}
				}
			}

			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					dy[x][y] = (int) ((dy[x][y] - min) / (double) (max - min) * 255);
					// System.out.println(n);
					int n = 0xff000000 | dy[x][y] << 16 | dy[x][y] << 8 | dy[x][y];
					img.setRGB(x, y, n);
				}
			}
		}else if(i==0) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			int arr2[][] = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					for (int a = -1; a <= 1; a++) {
						for (int b = -1; b <= 1; b++) {
							if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
								dx[x][y] += (img.getRGB(x + a, y + b) & 0x000000ff) * arr2[b + 1][a + 1];
								// System.out.println(pixel[x][y]);
							}
						}
					}
					if (dx[x][y] > max) {
						max = dx[x][y];
					}
					if (dx[x][y] < min) {
						min = dx[x][y];
					}
				}
			}

			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					dx[x][y] = (int) ((dx[x][y] - min) / (double) (max - min) * 255);
					// System.out.println(n);
					int n = 0xff000000 | dx[x][y] << 16 | dx[x][y] << 8 | dx[x][y];
					img.setRGB(x, y, n);
				}
			}
		}else if(i==3) {
			
		}
		return img;
		
	}
	
	public static BufferedImage Sobel(BufferedImage sobelHorizontalImg, BufferedImage sobelVerticalImg) {
		// TODO Auto-generated method stub
		
		BufferedImage img3 = new BufferedImage(sobelHorizontalImg.getWidth(), sobelHorizontalImg.getHeight(),sobelHorizontalImg.getType());
		for (int y = 0; y < img3.getHeight(); y++) {
			for (int x = 0; x < img3.getWidth(); x++) {
				int dx = sobelHorizontalImg.getRGB(x, y) & 0x000000ff;
				int dy = sobelVerticalImg.getRGB(x, y) & 0x000000ff;
				int n = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

				n = 0xff000000 | n << 16 | n << 8 | n;
				img3.setRGB(x, y, n);
			}
		}
		return img3;
	}

	public static BufferedImage Binarization(File f, int i) throws IOException {
		// XXX 使用 i 作為門檻值進行二值化
		i = 0xff000000 | i << 16 | i << 8 | i;
		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {

				if (img.getRGB(x, y) <= i) {
					img.setRGB(x, y, 0x000000);
				} else {
					img.setRGB(x, y, 0xffffff);
				}
			}
		}
		return img;
	}

	public static BufferedImage MedianFilter(File f, int i) throws IOException {
		// XXX 使用 i*i 大小的 Window 做 MedianFilter

		// 計算Window邊界與中心的距離
		i = i / 2;

		// 使用 ArrayList 把所有Pixel值存起來
		ArrayList<Integer> arr = new ArrayList<Integer>();

		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		// 為了在修改Pixel值後不影響計算，修改後Pixel值另外儲存
		BufferedImage img2 = ImageIO.read(f);

		for (int y = i; y < img.getHeight() - i; y++) {
			for (int x = i; x < img.getWidth() - i; x++) {
				arr.clear();
				for (int a = -i; a <= i; a++) {
					for (int b = -i; b <= i; b++) {
						if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
							try {
								arr.add(img.getRGB(x + a, y + b));
							} catch (ArrayIndexOutOfBoundsException e) {
								// TODO: handle exception
								System.out.println((x + a) + " " + (y + b));
							}
						}
					}
				}
				Collections.sort(arr);
				img2.setRGB(x, y, arr.get(arr.size() / 2));
			}
		}
		return img2;
	}

	public static BufferedImage MeanFilter(File f, int i) throws IOException {
		// XXX 使用 i*i 大小的 Window 做 MeanFilter

		int Number = i * i;
		// 計算Window邊界與中心的距離
		i = i / 2;
		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		// 為了在修改Pixel值後不影響計算，修改後Pixel值另外儲存
		BufferedImage img2 = ImageIO.read(f);
		int pixel;
		// 為了避免 Overflow ，邊界部分將被忽略
		for (int y = i; y < img.getHeight() - i; y++) {
			for (int x = i; x < img.getWidth() - i; x++) {
				pixel = 0;
				for (int a = -i; a <= i; a++) {
					for (int b = -i; b <= i; b++) {
						if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
							try {
								pixel += img.getRGB(x + a, y + b) & 0x000000ff;
							} catch (ArrayIndexOutOfBoundsException e) {
								// TODO: handle exception
								System.out.println((x + a) + " " + (y + b));
							}
						}
					}
				}
				pixel = pixel / Number;
				pixel = 0xff000000 | pixel << 16 | pixel << 8 | pixel;
				img2.setRGB(x, y, pixel);
			}
		}
		return img2;

	}

	public static BufferedImage MaxFilter(File f, int i) throws IOException {
		// XXX 使用 i*i 大小的 Window 做 MaxFilter

		// 計算Window邊界與中心的距離
		i = i / 2;
		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		BufferedImage img2 = ImageIO.read(f);
		int pixel;
		// 為了避免 Overflow ，邊界部分將被忽略
		for (int y = i; y < img.getHeight() - i; y++) {
			for (int x = i; x < img.getWidth() - i; x++) {
				pixel = Integer.MIN_VALUE;
				for (int a = -i; a <= i; a++) {
					for (int b = -i; b <= i; b++) {
						if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
							try {
								pixel = Math.max(img.getRGB(x + a, y + b), pixel);
							} catch (ArrayIndexOutOfBoundsException e) {
								// TODO: handle exception
								System.out.println((x + a) + " " + (y + b));
							}
						}
					}
				}
				img2.setRGB(x, y, pixel);
			}
		}
		return img2;
	}

	public static BufferedImage MinFilter(File f, int i) throws IOException {
		// XXX 使用 i*i 大小的 Window 做 MinFilter

		// 計算Window邊界與中心的距離
		i = i / 2;
		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);
		BufferedImage img2 = ImageIO.read(f);
		int pixel;
		// 為了避免 Overflow ，邊界部分將被忽略
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				pixel = Integer.MAX_VALUE;
				for (int a = -i; a <= i; a++) {
					for (int b = -i; b <= i; b++) {
						if ((x + a >= 0 && y + b >= 0) && (x + a < img.getWidth() && y + b < img.getHeight())) {
							try {
								pixel = Math.min(img.getRGB(x + a, y + b), pixel);
							} catch (ArrayIndexOutOfBoundsException e) {
								// TODO: handle exception
								System.out.println((x + a) + " " + (y + b));
							}
						}
					}
				}
				img2.setRGB(x, y, pixel);
			}
		}
		return img2;
	}

	public static BufferedImage SaltAndPepper(File f,int r) throws IOException {
		// XXX 以輸入值作為雜訊數量的依據放入 SaltAndPepper 雜訊
		r=11-r;
		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int n = (int) (Math.random() * 100) % r;
				int pixel = img.getRGB(x, y);
				if (n == 0) {
					pixel = 0x000000;
				} else if (n == 1) {
					pixel = 0xffffff;
				}
				img.setRGB(x, y, pixel);
			}
		}
		return img;
		
	}

	public static BufferedImage Negative(File f) throws IOException {
		// XXX 將影像轉換為負片

		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				// 讀取圖片座標(x,y)的RGB值
				int pixel = img.getRGB(x, y);

				// 計算RGB各值
				int red = 255 - ((pixel & 0xff0000) >> 16);
				int green = 255 - ((pixel & 0x00ff00) >> 8);
				int blue = 255 - (pixel & 0x0000ff);

				pixel = (red << 16) | (green << 8) | blue;
				// 改變Pixel值
				img.setRGB(x, y, pixel);
			}
		}
		return img;
		
	}

	public static BufferedImage Gray(File f) throws IOException {
		// XXX 將影像轉換為灰階

		// 為了修改Pixel值，需要把圖檔轉成BufferedImage物件
		BufferedImage img = ImageIO.read(f);

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				// 讀取圖片座標(x,y)的RGB值
				int pixel = img.getRGB(x, y);

				// 計算RGB各值
				int red = (pixel & 0xff0000) >> 16;
				int green = (pixel & 0x00ff00) >> 8;
				int blue = pixel & 0x0000ff;

				// 計算灰階單一值
				int gray = (red + green + blue) / 3;

				// 組合成灰階值
				pixel = 0xff000000 | (gray << 16) | (gray << 8) | gray;

				// 改變Pixel值
				img.setRGB(x, y, pixel);
			}
		}
		
		return img;
	}

	public static void saveImg(BufferedImage img, String fileName) throws IOException {
		// 儲存照片
		File outputFile = new File(fileName);
		ImageIO.write(img, "jpg", outputFile);
	}
}