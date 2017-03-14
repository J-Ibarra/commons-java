package br.com.crypto.commons.amazon.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;

import br.com.crypto.commons.amazon.S3Manager;
import br.com.crypto.commons.logger.LoggerUtils;
import br.com.crypto.commons.logger.helper.CommonsMessage;

public abstract class S3AbstractManager implements S3Manager {

	S3Service s3Service = null;
	S3Bucket bucket = null;

	public S3AbstractManager() {

		AWSCredentials awsCredentials = new AWSCredentials(this.getAwsAccessKey(), this.getAwsSecretKey());
		this.s3Service = new RestS3Service(awsCredentials);
		try {
			this.bucket = this.s3Service.getBucket(this.getBucket());
		} catch (S3ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
		}
	}

	@Override
	public boolean delete(String amazonPath) {

		try {
			s3Service.deleteObject(bucket, amazonPath);
			return true;
		} catch (S3ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return false;
		}

	}

	@Override
	public boolean send(File fileToSend, String amazonPath) {
		
		try {

			S3Object s3Object = new S3Object(fileToSend);
			s3Object.setKey(amazonPath);

			s3Service.putObject(bucket, s3Object);

		} catch (NoSuchAlgorithmException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return false;
		} catch (S3ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return false;
		} catch (IOException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return false;
		}

		return true;

	}

	@Override
	public InputStream download(String amazonPath) {

		try {
			@SuppressWarnings("deprecation")
			S3Object s3Object = s3Service.getObject(bucket, amazonPath);
			return s3Object.getDataInputStream();
		} catch (S3ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return null;
		} catch (ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return null;
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> listNames(String amazonPath) {

		try {

			List<String> names = new ArrayList<>();
			S3Object[] s3ObjectArray = s3Service.listObjects(bucket, amazonPath, "/");

			for (S3Object s3Object : s3ObjectArray) {

				names.add(s3Object.getKey());

			}

			return names;

		} catch (S3ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return null;
		}

	}

	@Override
	public List<File> listFiles(String amazonPath) {

		try {

			List<File> files = new ArrayList<>();
			S3Object[] s3ObjectArray = s3Service.listObjects(bucket, amazonPath, "/");

			for (S3Object s3Object : s3ObjectArray) {

				files.add(s3Object.getDataInputFile());

			}

			return files;

		} catch (S3ServiceException e) {
			LoggerUtils.errorLog(this.getClass(), e, CommonsMessage.s3GenericException.toString());
			return null;
		}
		
	}

	@Override
	public File getUniqueFileInsideDirectory(String amazonPath) {
		List<File> lFiles = listFiles(amazonPath);
		return lFiles!=null&&lFiles.size()>0?lFiles.get(0):null;
	}

}
