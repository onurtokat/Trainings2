package com.onurtokat;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class AggregationMapReduce {

    public static class AggregationMapper
            extends Mapper<Object, Text, Text, IntWritable>
    {
        /**
         * map() gets a key, value, and context (which we'll ignore for the moment).
         * key - Let's use a fictitious number , say 5
         * value - the current line; we are being fed one line at a time from the
         *         input file
         *
         * here's what the key and value look like if i print them out with the first
         * println statement below:
         *
         * [map] key: (5), value: (the first number of flowers picked)
         * [map] key: (5), value: (the second number of flowers picked)
         * [map] key: (5), value: (the third number of flowers picked)
         */
        private Text  word = new Text();
        public void map(Object key,
                        Text value,
                        Context context)
                throws IOException, InterruptedException
        {
            StringTokenizer tokenizer = new StringTokenizer(value.toString(), " \t\n\r\f,.:;?![]'");
            while (tokenizer.hasMoreTokens())
            {
                // make the words lowercase so words like "an" and "An" are counted as one word
                String s = tokenizer.nextToken().toLowerCase().trim();
                IntWritable val = new IntWritable(Integer.parseInt(s));
                word.set("aggregate");
                context.write(word, val);
            }
        }
    }

    /**
     * this is the reducer class.
     * some magic happens before the data gets to us. the key and values data looks like this:
     *
     * [reduce] key: (agregate), value: (5,10,20,30)
     * ...
     *
     */
    public static class AggregationReducer
            extends Reducer<Text, IntWritable, Text, IntWritable>
    {
        private IntWritable total = new IntWritable();

        public void reduce(Text key,
                           Iterable<IntWritable> values,
                           Context context)
                throws IOException, InterruptedException
        {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            total.set(sum);
            // this writes the word and the count, like this: ("aggregate", 2)
            context.write(key, total);
        }
    }

    /**
     * the "driver" class. it sets everything up, then gets it started.
     */
    public static void main(String[] args)
            throws Exception
    {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2)
        {
            System.err.println("Usage: AggregationMapReduce  <inputFile> <outputDir>");
            System.exit(2);
        }
        Job job = new Job(conf, "JavaWorld Aggregation MaReduce");
        job.setJarByClass(AggregationMapReduce.class);
        job.setMapperClass(AggregationMapper.class);
        job.setCombinerClass(AggregationReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}