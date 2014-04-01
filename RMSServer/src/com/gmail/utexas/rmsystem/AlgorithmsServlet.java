package com.gmail.utexas.rmsystem;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.gmail.utexas.rmsystem.algorithms.AccelerometerAlgorithm;
import com.gmail.utexas.rmsystem.algorithms.AlgorithmQueue.PullQueue;
import com.gmail.utexas.rmsystem.algorithms.AlgorithmQueue.PushQueue;
import com.google.appengine.api.ThreadManager;

public class AlgorithmsServlet extends HttpServlet{
	

	Logger log = Logger.getLogger(AlgorithmsServlet.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("Starting algorithms!");
		PushQueue push = new PushQueue();
		PullQueue pull = new PullQueue();
		
		/*ExecutorService pool = Executors.newCachedThreadPool();
		pool.submit(push);
		pool.submit(pull);*/
		
		Thread pushThread = ThreadManager.createBackgroundThread(push);
		Thread pullThread = ThreadManager.createBackgroundThread(pull);
		
		pushThread.start();
		pullThread.start();
		log.info("Launching algorithm threads!");
	}

}
