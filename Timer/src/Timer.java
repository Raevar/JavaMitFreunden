
public class Timer implements Runnable
{
	private long	interval;
	private long	intervalCount;
	private long	lastLoopTime;
	private Updater	instance;
	private Thread	thread;

	// param interval: length of the interval in millisecconds
	// param object: the Object which shall be updated;
	
	public Timer(long interval, Updater instance)
	{
		this.interval = interval;
		intervalCount = 0;
		this.instance = instance;
		thread = new Thread(this);
		thread.isDaemon();
		thread.start();
	}

	public void run()
	{
		lastLoopTime = System.nanoTime();
		long delta = 0;
		while (true)
		{
			long currentLoopTime = System.nanoTime();
			delta += currentLoopTime - lastLoopTime;
			lastLoopTime = currentLoopTime;
			if (delta >= interval)
			{
				delta -= interval;
				instance.update();
				intervalCount++;
			}
		}
	}

	public void reset()
	{
		intervalCount = 0;
	}

	public long getInterval()
	{
		return interval;
	}

	public void setInterval(long interval)
	{
		this.interval = interval;
	}

	public long getIntervalCount()
	{
		return intervalCount;
	}

	public Thread getThread()
	{
		return thread;
	}
}
