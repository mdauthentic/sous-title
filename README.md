# Sous-title
[![Build Status](https://travis-ci.org/mdauthentic/sous-title.svg?branch=master)](https://travis-ci.org/mdauthentic/sous-title)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/mdauthentic/sous-title)


Read and convert subtitle (`.srt`) file to `csv` or `List`


## build.sbt
```scala
libraryDependencies += "io.github.mdauthentic" % "sous-title_2.13" % "0.3.0"
```

## Getting started

### Import
```scala
import io.github.mdauthentic.core._
```
### Reading example

Calling the `open` or `readInLine` method returns an `SRT` type containing `id`, `startTime`, `endTime` and `sub` (the subtitle itself).

```scala
scala> val reader = SRTReader.open("file.srt")
reader: List(SRT(1, 00:00:33.599, 00:00:35.270, List(Soy Amelia Folch.)))

```

### Inline reader example
Inline reader returns a list of `.srt` type

```scala
scala> val srt =
      """1
        |00:00:33,599 --> 00:00:35,270
        |(NARRA) Soy Amelia Folch.
        |
        |2
        |00:00:36,199 --> 00:00:39,870
        |Tengo 23 años y sin embargo
        |he salvado la vida del Empecinado.""".stripMargin
```

```scala
scala> val inlineReader = SRTReader.readInLine(srt)
inlineReader: List(SRT(1,00:00:33.599,00:00:35.270,List((NARRA) Soy Amelia Folch.)), SRT(2,00:00:36.199,00:00:39.870,List(Tengo 23 años y sin embargo, he salvado la vida del Empecinado.)))
```

### Extracting item
If you are interested in only some part of the result returned by the `reader`, for instance the `subtitle` and not the rest i.e. `id`, `start` and `end time`, then you can extract just the subtitle by doing something like this;

```scala
scala> inlineReader.sub
List(List((NARRA) Soy Amelia Folch.), List(Tengo 23 años y sin embargo, he salvado la vida del Empecinado.))
```

### Writing example
There are two ways to write to file;

- writing without header

```scala
scala> val reader = SRTReader.open("file.srt")
reader: List[SRT] = List(SRT(1, 00:00:33.599, 00:00:35.270, List(Soy Amelia Folch.)))
```

```scala
scala> SRTWriter.write(reader, "output.csv")
```

using file path directly

```scala
scala> SRTWriter.write("inputFileName.srt", "outputFileName.csv")
```

- with user-defined header

```scala
scala> val header = List("id", "start_time", "end_time", "subtitle")
header: List[String] = List(id, start_time, end_time, subtitle)
```

```
scala> SRTWriter.write("input.srt", "output.csv", header)
```

## Motivation
In `Scandal` (a TV series), `wine` was mentioned several times and I was curious to know the number of times the word was used in the entire series (from seasons 1 - 7). This library was used to convert all the subtitle files for this series to `csv` format for further analysis.

This library will come in handy in data analysis projects for parsing and extracting the contents of subtitle files.

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
