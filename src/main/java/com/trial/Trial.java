package com.trial;

import java.util.Map;

import com.zkteco.biometric.FingerprintCaptureListener;
import com.zkteco.biometric.FingerprintSensor;

public class Trial extends FingerprintSensor {

	
 
	@Override
	public int DBAdd(int fid, byte[] regTemplate) {
		// TODO Auto-generated method stub
		return super.DBAdd(fid, regTemplate);
	}

	@Override
	public int DBCount() {
		// TODO Auto-generated method stub
		return super.DBCount();
	}

	@Override
	public int DBDel(int fid) {
		// TODO Auto-generated method stub
		return super.DBDel(fid);
	}

	@Override
	public int ExtractFromImage(String filePath, int DPI, byte[] template, int[] size) {
		// TODO Auto-generated method stub
		return super.ExtractFromImage(filePath, DPI, template, size);
	}

	@Override
	public int GenRegFPTemplate(byte[] temp1, byte[] temp2, byte[] temp3, byte[] regTemp, int[] regTempLen) {
		// TODO Auto-generated method stub
		return super.GenRegFPTemplate(temp1, temp2, temp3, regTemp, regTempLen);
	}

	@Override
	public int GetParameter(int code, byte[] value, int[] len) {
		// TODO Auto-generated method stub
		return super.GetParameter(code, value, len);
	}

	@Override
	public int IdentifyFP(byte[] template, int[] fid, int[] socre) {
		// TODO Auto-generated method stub
		return super.IdentifyFP(template, fid, socre);
	}

	@Override
	public int MatchFP(byte[] temp1, byte[] temp2) {
		// TODO Auto-generated method stub
		return super.MatchFP(temp1, temp2);
	}

	@Override
	public int SetParameter(int code, byte[] value, int len) {
		// TODO Auto-generated method stub
		return super.SetParameter(code, value, len);
	}

	@Override
	public int VerifyFPByID(int fid, byte[] template) {
		// TODO Auto-generated method stub
		return super.VerifyFPByID(fid, template);
	}

	@Override
	public int capture(byte[] image, byte[] template, int[] templen) {
		// TODO Auto-generated method stub
		return super.capture(image, template, templen);
	}

	@Override
	public int closeDevice() {
		// TODO Auto-generated method stub
		return super.closeDevice();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public String getDevSn() {
		// TODO Auto-generated method stub
		return super.getDevSn();
	}

	@Override
	public int getDeviceCount() {
		// TODO Auto-generated method stub
		return super.getDeviceCount();
	}

	@Override
	public String getDeviceTag() {
		// TODO Auto-generated method stub
		return super.getDeviceTag();
	}

	@Override
	public int getFakeFunOn() {
		// TODO Auto-generated method stub
		return super.getFakeFunOn();
	}

	@Override
	public int getFakeStatus() {
		// TODO Auto-generated method stub
		return super.getFakeStatus();
	}

	@Override
	public Map<String, FingerprintCaptureListener> getFingerprintCaptureListenerList() {
		// TODO Auto-generated method stub
		return super.getFingerprintCaptureListenerList();
	}

	@Override
	public int getImageHeight() {
		// TODO Auto-generated method stub
		return super.getImageHeight();
	}

	@Override
	public int getImageWidth() {
		// TODO Auto-generated method stub
		return super.getImageWidth();
	}

	@Override
	public int getLastTempLen() {
		// TODO Auto-generated method stub
		return super.getLastTempLen();
	}

	@Override
	public int openDevice(int index) {
		 System.out.println(index);
		return super.openDevice(index);
	}

	@Override
	public void setFakeFunOn(int FakeFunOn) {
		// TODO Auto-generated method stub
		super.setFakeFunOn(FakeFunOn);
	}

	@Override
	public void setFingerprintCaptureListener(FingerprintCaptureListener listener) {
		// TODO Auto-generated method stub
		super.setFingerprintCaptureListener(listener);
	}

	@Override
	public void setLastTempLen(int len) {
		// TODO Auto-generated method stub
		super.setLastTempLen(len);
	}

	@Override
	public boolean startCapture() {
		// TODO Auto-generated method stub
		return super.startCapture();
	}

	@Override
	public void stopCapture() {
		// TODO Auto-generated method stub
		super.stopCapture();
	}
}
