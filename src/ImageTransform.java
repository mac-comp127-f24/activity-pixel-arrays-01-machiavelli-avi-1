import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class ImageTransform {

    public static Image lighten(Image srcImage) {
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.RGB);
        for(int i =0; i<pixels.length; i++){
            pixels[i]*=1.5;
        }
        srcImage = new Image(srcImage.getImageWidth(),srcImage.getImageHeight(),pixels,Image.PixelFormat.RGB);
        return srcImage;
        //throw new UnsupportedOperationException("Method not yet defined");
    }


    public static Image greenShift(Image srcImage) {
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.RGB);
        for(int i =0; i<pixels.length; i++){
            if(i%3==2){
                pixels[i]*=0.25;
        }
    }
        srcImage = new Image(srcImage.getImageWidth(),srcImage.getImageHeight(),pixels,Image.PixelFormat.RGB);
        return srcImage;
        //throw new UnsupportedOperationException("Method not yet defined");
    }

    public static Image invert(Image srcImage) {
        byte[] pixels = srcImage.toByteArray(Image.PixelFormat.RGB);
        for(int i =0; i<pixels.length; i++){
            pixels[i] = (byte)((255)-pixels[i]);
        }
        srcImage = new Image(srcImage.getImageWidth(),srcImage.getImageHeight(),pixels,Image.PixelFormat.RGB);
        return srcImage;

        //throw new UnsupportedOperationException("Method not yet defined");
    }

    public static Image grayScale(Image srcImage) {
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.RGB);
        for(int i =0; i<pixels.length; i+=3){
            float average = (pixels[i]+pixels[i+1]+pixels[i+2])/3;
            pixels[i]=(float) (average*.5);
            pixels[i+1]=(float) (average*.5);
            pixels[i+2]=(float) (average*.5);
        }
        srcImage = new Image(srcImage.getImageWidth(),srcImage.getImageHeight(),pixels,Image.PixelFormat.RGB);
        return srcImage;

        //throw new UnsupportedOperationException("Method not yet defined");
    }


    public static void main(String[] args) {
        Image srcImage = new Image("mscs-shield.png");
    
        Scanner scan = new Scanner(System.in);
        System.out.println("How would you like to transform your image?");
        System.out.println("1. Lighten");
        System.out.println("2. Green Shift");
        System.out.println("3. Invert");
        System.out.println("4. Grayscale");

        System.out.print("> ");
        int choice = scan.nextInt();

        Image transformed = switch(choice) {
            default -> srcImage; // If no matching choice, display original image
            case 1 -> lighten(srcImage);
            case 2 -> greenShift(srcImage);
            case 3 -> invert(srcImage);
            case 4 -> grayScale(srcImage);
        };

        CanvasWindow canvas = new CanvasWindow("img", 500, 500);
        canvas.add(transformed);
        transformed.setCenter(canvas.getCenter());

        scan.close();
    }
    
}
