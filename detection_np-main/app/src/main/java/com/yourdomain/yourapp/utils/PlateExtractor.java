import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

public class PlateExtractor {
    private static final String TAG = "PlateExtractor";

    public static String extractPlateNumber(Bitmap photo) {
        String plateNumber = "";

        try {
            TessBaseAPI tessBaseApi = new TessBaseAPI();
            // Replace "eng" with the appropriate language code for your OCR needs
            tessBaseApi.init("path_to_tessdata", "eng"); // Replace "path_to_tessdata" with the actual path to your tessdata folder

            tessBaseApi.setImage(photo);
            plateNumber = tessBaseApi.getUTF8Text();

            tessBaseApi.end();

            // Post-process the extracted plate number if needed (e.g., remove special characters, format)

            // Log the extracted plate number for debugging
            Log.d(TAG, "Extracted Plate Number: " + plateNumber);
        } catch (Exception e) {
            Log.e(TAG, "Failed to extract plate number: " + e.getMessage());
        }

        return plateNumber;
    }
}
