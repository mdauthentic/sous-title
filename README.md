# SRT to CSV converter

Read and convert .srt file to csv


```scala
import com.sous.title.core._
```
### Reading example
```
val reader = new SRTReader().open("file.srt")
```
### Writing example
```
SRTWriter.SRT2CSV("input.srt", "output.csv")
```
