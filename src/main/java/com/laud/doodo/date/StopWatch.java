package com.laud.doodo.date;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 秒表
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-7-13 下午4:12:39
 * @copyright: www.dreamoriole.com
 * 
 */
public class StopWatch extends TimerTask {
	private final static Logger log = LoggerFactory.getLogger(StopWatch.class);
	private static final String NAME = "STOP_WATCH";
	private Timer timer = null;
	private StopWatchListener listener = null;
	private Date date = null;
	private int elapseTime = 0;
	private int tick = 0;
	private EnumStopWatchState state;

	public StopWatch() {
		this.state = EnumStopWatchState.STOP;
	}

	/**
	 * 启动秒表
	 */
	public void start() {
		if (state == EnumStopWatchState.STOP) {
			timer = new Timer(NAME);
			timer.scheduleAtFixedRate(this, 0, 1000);
			state = EnumStopWatchState.RUNNING;
			log.info("start stopwatch, it's state:" + state + ", elapseTime:"
					+ elapseTime);
		}
	}

	/**
	 * 停止秒表
	 */
	public void stop() {
		timer.cancel();
		timer = null;
		tick = 0;
		elapseTime = 0;
		state = EnumStopWatchState.STOP;
		log.info("stop stopwatch, it's state:" + state + ", elapseTime:"
				+ elapseTime);
	}

	/**
	 * 暂停秒表
	 * 
	 * @return
	 */
	public boolean pause() {
		if (state == EnumStopWatchState.RUNNING) {
			state = EnumStopWatchState.PAUSE;
			log.info("pause stopwatch, it's state:" + state + ", elapseTime:"
					+ elapseTime);
			return true;
		}
		return false;
	}

	/**
	 * 恢复秒表
	 * 
	 * @return
	 */
	public boolean resume() {
		if (state == EnumStopWatchState.PAUSE) {
			state = EnumStopWatchState.RUNNING;
			log.info("resume stopwatch, it's state:" + state + ", elapseTime:"
					+ elapseTime);
			return true;
		}
		return false;
	}

	/**
	 * 注册秒表监听器
	 * 
	 * @param listener
	 */
	public void registerStopWatchListener(StopWatchListener listener) {
		this.listener = listener;
	}

	/**
	 * 秒表监听器
	 * 
	 * @author: Laud
	 * @email: htd0324@gmail.com
	 * @date: 2013-7-13 下午4:16:26
	 * @copyright: www.dreamoriole.com
	 * 
	 */
	public interface StopWatchListener {
		/**
		 * 计数
		 * 
		 * @param date
		 *            当前秒表时间
		 * @param tick
		 *            当前秒数
		 * @param elapseTime
		 *            已逝去时间
		 */
		public void countdown(Date date, int tick, int elapseTime);
	}

	@Override
	public void run() {
		++elapseTime;
		if (state == EnumStopWatchState.RUNNING) {
			++tick;
		}
		log.info("stopwatch state:" + state + ", elapseTime:" + elapseTime);
		if (listener != null) {
			if (date == null) {
				date = new Date();
			}
			date.setTime(System.currentTimeMillis());
			listener.countdown(date, tick, elapseTime);
		}
	}

	/**
	 * 秒表状态，枚举
	 * 
	 * @author: Laud
	 * @email: htd0324@gmail.com
	 * @date: 2013-7-13 下午6:34:12
	 * @copyright: www.dreamoriole.com
	 * 
	 */
	public enum EnumStopWatchState {
		/**
		 * 已启动状态
		 */
		RUNNING,
		/**
		 * 暂停状态
		 */
		PAUSE,
		/**
		 * 停止状态
		 */
		STOP
	}
}
