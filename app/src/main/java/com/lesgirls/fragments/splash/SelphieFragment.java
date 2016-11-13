package com.lesgirls.fragments.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.lesgirls.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SelphieFragment extends Fragment {
    private static final String TAG = "SelphieFragment";
    private OnSelphie listener;
    private SurfaceView sv;
    private SurfaceHolder svHolder;
    private Camera camera;
    private int idCamera;
    private boolean isFrontCamera;
    private RelativeLayout rlChangeCameras;
    private RelativeLayout rlFlash;
    private ImageView ivFlash;
    private ImageView ivChangeCameras;
    private RelativeLayout rlTakePhoto;
    private ImageView ivSelphie;
    private View global;
    private boolean onFlash = false;



    public SelphieFragment() {
    }
    public static SelphieFragment newInstance() {
        SelphieFragment fragment = new SelphieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selphie, container, false);
    }
    @Override
    public void onViewCreated(final View view, Bundle saved) {
        super.onViewCreated(view, saved);
        ivSelphie = (ImageView) view.findViewById(R.id.ivSelphie);
        ivSelphie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivSelphie.setVisibility(View.INVISIBLE);
                sv.setVisibility(View.VISIBLE);
                initCamera();
            }
        });
        idCamera = Camera.getNumberOfCameras()>1?Camera.CameraInfo.CAMERA_FACING_FRONT:Camera.CameraInfo.CAMERA_FACING_BACK;
        rlChangeCameras = (RelativeLayout) view.findViewById(R.id.rlChangeCameras);
        rlChangeCameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Camera.getNumberOfCameras() > 1) {
                    if (camera != null) {
                        camera.stopPreview();
                        camera.release();
                        camera = null;
                    }
                    idCamera = (idCamera == Camera.CameraInfo.CAMERA_FACING_FRONT) ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;
                    if (camera == null) {
                        initCamera();
                    }
                }
            }
        });
        rlFlash = (RelativeLayout) view.findViewById(R.id.rlFrash);
        rlFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFlash = !onFlash;
                ivFlash.setImageResource(onFlash?R.drawable.ic_flash_on:R.drawable.ic_flash_off);
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(onFlash?Camera.Parameters.FLASH_MODE_ON:Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(p);
            }
        });
        ivFlash = (ImageView) view.findViewById(R.id.ivFlash);
        rlTakePhoto = (RelativeLayout) view.findViewById(R.id.rlTakePhoto);
        rlTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera aCamera) {
                        Bitmap bitmap = createPhoto(data);
                        if (camera != null) {
                            camera.stopPreview();
                            camera.release();
                            camera = null;
                        }
                        sv.setVisibility(View.INVISIBLE);
                        ivSelphie.setVisibility(View.VISIBLE);
                        ivSelphie.setImageBitmap(bitmap);
                                FaceDetector detector = new FaceDetector.Builder(getContext())
                                .setTrackingEnabled(false)
                                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                                .build();

                        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                        SparseArray<Face> faces = detector.detect(frame);

                        Toast.makeText(getActivity(),"Detected "+faces.size()+" faces",Toast.LENGTH_LONG).show();
                        Uri uri = save(bitmap);
                        //if(faces.size()>0) listener.onTake(uri);
                        listener.onTake(uri);
                    }
                });
            }
        });
        global = view;
        initCamera();
    }
    private void initCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        if (camera == null) {
            Log.d(TAG, "Camera null");
            try {
                if(Camera.getNumberOfCameras()>1){
                    camera = Camera.open(idCamera);
                }
                else{
                    camera = Camera.open();
                }
                initSurface();
                camera.setPreviewDisplay(svHolder);
                setCameraDisplayOrientation(idCamera);
                //setPreviewSize(FULL_SCREEN);
                camera.startPreview();

                //Initialize camera flash mode
                initCameraFlashMode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void initSurface() {
        sv = (SurfaceView) global.findViewById(R.id.svSelphie);
        svHolder = sv.getHolder();
        svHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        svHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    setCameraDisplayOrientation(idCamera);
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    Log.d(TAG, "" + ex.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                try {
                    camera.stopPreview();
                    setCameraDisplayOrientation(idCamera);
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }
    private void initCameraFlashMode() {
        if (idCamera == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            ivFlash.setEnabled(false);
            ivFlash.setImageResource(R.drawable.ic_flash_off_disabled);
        } else {
            //Set camera flash mode off
            ivFlash.setImageResource(onFlash?R.drawable.ic_flash_on:R.drawable.ic_flash_off);
            Camera.Parameters p = camera.getParameters();
            p.setFlashMode(onFlash?Camera.Parameters.FLASH_MODE_ON:Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelphie) {
            listener = (OnSelphie) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    private void setCameraDisplayOrientation(int cameraId) {
        // определяем насколько повернут экран от нормального положения
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result = 0;

        // получаем инфо по камере cameraId
        Camera.CameraInfo info = new Camera.CameraInfo();
        if(Camera.getNumberOfCameras()>1) {
            Camera.getCameraInfo(cameraId, info);
        }
        else{
            Camera.getCameraInfo(0, info);
        }
        // задняя камера
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            result = ((360 - degrees) + info.orientation);
        } else
            // передняя камера
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = ((360 - degrees) - info.orientation);
                result += 360;
            }
        result = result % 360;
        camera.setDisplayOrientation(result);
    }
    private Bitmap createPhoto(byte[] data){
        BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
        bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;

        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, bitmapFatoryOptions);
        Bitmap res = null;
        if(bitmap != null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float dx = 1;
            float dy = 1;
            float scale = 1;
            Log.i(TAG, "Width:"+width+" height:"+height);
            if(width>=2048||height>=2048){
                dx = 2048f/((float)width);
                dy = 2048f/((float)height);
                scale = ((dx<dy)?dx:dy);
                Log.i(TAG, "Scale:"+scale);
            }
            width = Math.round((((float)width)*scale));
            height = Math.round((((float)height)*scale));
            Log.i(TAG, "Width:"+width+" height:"+height);
            int angle = ((idCamera == Camera.CameraInfo.CAMERA_FACING_FRONT)?-90:90);
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            matrix.postRotate(angle);
            res = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        return res;
    }
    private Uri save(Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public interface OnSelphie {
        void onTake(Uri uri);
    }
}
