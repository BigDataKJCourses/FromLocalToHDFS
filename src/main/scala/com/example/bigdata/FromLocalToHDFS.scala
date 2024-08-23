package com.example.bigdata

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import com.alvinalexander.accesslogparser._
import com.example.bigdata.tools.GetContext.getSparkContext

object FromLocalToHDFS {

  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: FromLocalToHDFS <localDirectory> <hdfsDirectory>")
      System.exit(1)
    }

    val localDirectory = args(0)
    val hdfsDirectory = args(1)

    val sc = getSparkContext("FromLocalToHDFS")
    val parser = new AccessLogParser

    val logLines = sc.textFile(localDirectory)
    val logRecords = logLines.map(line => parser.parseRecord(line))

    logRecords.foreach {
      case Some(i) => println(i.clientIpAddress)
      case None => println("not recognised")
    }
  }
}
