import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountryOverTime {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: CountryOverTimeMapper <input path> <output path>");
      System.exit(-1);
    }

    //define the job to the JobTracker
    Job job = Job.getInstance();
    job.setJarByClass(CountryOverTime.class);
    job.setJobName("Country Temperatures Over Time");

    // set the input and output paths (passed as args)
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // set the Mapper and Reducer classes to be called
    job.setMapperClass(CountryOverTimeMapper.class);
    job.setReducerClass(CountryOverTimeReducer.class);

    // set the format of the keys and values
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(DoubleWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(DoubleWritable.class);

    // submit the job and wait for its completion
    job.waitForCompletion(true);

  }
}
