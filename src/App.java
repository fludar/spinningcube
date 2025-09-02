public class App {
    static class CubeRenderer {
        float A = 0, B = 0, C = 0;

        int width = 160, height = 44;
        float[] zBuffer = new float[160 * 44];
        char[] buffer = new char[160 * 44];

        int backgroundASCIICode = ' ';
        int distanceFromCam = 100;
        float horizontalOffset;
        float K1 = 40;
        float incrementSpeed = 0.6f;
    
        float calculateX(float i, float j, float k) {
            return (j * (float)Math.sin(A) * (float)Math.sin(B) * (float)Math.cos(C)
                  - k * (float)Math.cos(A) * (float)Math.sin(B) * (float)Math.cos(C)
                  + j * (float)Math.cos(A) * (float)Math.sin(C)
                  + k * (float)Math.sin(A) * (float)Math.sin(C)
                  + i * (float)Math.cos(B) * (float)Math.cos(C));
        }

        float calculateY(float i, float j, float k) {
            return (j * (float)Math.cos(A) * (float)Math.cos(C)
                  + k * (float)Math.sin(A) * (float)Math.cos(C)
                  - j * (float)Math.sin(A) * (float)Math.sin(B) * (float)Math.sin(C)
                  + k * (float)Math.cos(A) * (float)Math.sin(B) * (float)Math.sin(C)
                  - i * (float)Math.cos(B) * (float)Math.sin(C));
        }

        float calculateZ(float i, float j, float k) {
            return (k * (float)Math.cos(A) * (float)Math.cos(B)
                  - j * (float)Math.sin(A) * (float)Math.cos(B)
                  + i * (float)Math.sin(B));
        }
        
        void calculateForSurface(float cubeX, float cubeY, float cubeZ, char ch) {
            float x = calculateX(cubeX, cubeY, cubeZ);
            float y = calculateY(cubeX, cubeY, cubeZ);
            float z = calculateZ(cubeX, cubeY, cubeZ) + distanceFromCam;

            float ooz = 1 / z;

            int xp = (int)(width / 2 + horizontalOffset + K1 * ooz * x * 2);
            int yp = (int)(height / 2 + K1 * ooz * y);

            int idx = xp + yp * width;
            if (idx >= 0 && idx < width * height) {
                if (ooz > zBuffer[idx]) {
                    zBuffer[idx] = ooz;
                    buffer[idx] = ch;
                }
            }
        }
        
    }
    public static void main(String[] args) throws Exception {
        
    }
}
