package org.bluk.avmeister.abstracts.queue;

import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.exceptions.queue.QueueAlreadyRunningException;
import org.bluk.avmeister.exceptions.queue.QueueNotStartedException;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AbstractQueue<T extends AbstractQueueTask> {
    private final List<T> tasks = new ArrayList<>();
    private T currentTask;

    private BukkitTask task;

    public AbstractQueue() throws QueueAlreadyRunningException {
        this(true);
    }

    public AbstractQueue(boolean startImmediately) throws QueueAlreadyRunningException {
        // Starting out bukkit task
        if (startImmediately) {
            this.start();
        }
    }

    public int size() {
        return tasks.size();
    }

    public void add(T task) {
        this.tasks.add(task);
    }

    public void start() throws QueueAlreadyRunningException {
        if (task != null && !task.isCancelled()) {
            throw new QueueAlreadyRunningException();
        }

        task = Avmeister.instance.getServer().getScheduler().runTaskTimer(Avmeister.instance, () -> {
            // Checking if our current task is fulfilled or no
            if (currentTask != null && currentTask.status != QueueTaskStatus.FULFILLED) return;

            // Getting list of current tasks
            if (tasks.size() > 0) {
                var nextTask = tasks.get(0);

                // Starting nextTask
                currentTask = nextTask;
                nextTask.handler();
            }
        }, 0, 250);
    }

    public void stop() throws QueueNotStartedException {
        if (task == null || task.isCancelled()) {
            throw new QueueNotStartedException();
        }

        task.cancel();
    }
}
