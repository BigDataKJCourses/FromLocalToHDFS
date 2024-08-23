package com.example.bigdata.tools

import org.apache.spark.{SparkConf, SparkContext}

object GetContext {

  def getSparkContext(appName: String): SparkContext = {
    val master = sys.props.getOrElse("spark.master", "local[*]")

    val conf: SparkConf = new SparkConf()
      .setAppName(appName)
      .setMaster(master)

    val sc = SparkContext.getOrCreate(conf)

    sc
  }
}
