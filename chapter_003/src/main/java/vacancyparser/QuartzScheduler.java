package vacancyparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by User2 on 30.06.2018.
 */
public class QuartzScheduler {
    public static void init(Class jobClass, String cronExpression) {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity("VacancyParser job", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("VacancyParser trigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cronExpression)
                ).build();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
