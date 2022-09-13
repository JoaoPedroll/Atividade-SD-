import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

public class Receber {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(13085);
        System.out.println("Servidor esperando o envio da imagem");

        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        System.out.println("Reading image: ");

        byte[] sizeAr = new byte[4343];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);
        System.out.println("Numero de Bytes da imagem recebida:"+size);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        System.out.println("Recebida:" + image.getHeight() + "x" + image.getWidth() );

        ImageIO.write(image, "jpg", new File
                ("C:\\Users\\joaop\\Desktop\\recebida do servidor\\recebida pelo servidor.jpg"));

        serverSocket.close();
    }

}