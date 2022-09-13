import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class Enviar {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 13085);
        OutputStream outputStream = socket.getOutputStream();

        BufferedImage image = ImageIO.read(new File
                ("C:\\Users\\joaop\\Desktop\\enviada pelo cliente\\imagem a ser enviada.jpg"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4343).putInt(byteArrayOutputStream.size()).array();
        System.out.println("Quantidade de Bytes da imagem:" + byteArrayOutputStream.size());

        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        System.out.println("Imagem enviada, fechando Socket...");

        socket.close();
    }
}