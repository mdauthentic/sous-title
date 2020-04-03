# Sous-title

SRT to CSV converter

Read and convert .srt file to csv


```scala
import com.sous.title.core._
```
### Reading example
```
scala> val reader = new SRTReader().open("file.srt")
reader: List(SRT(1,00:00:33.599 --> 00:00:35.270,List((NARRA) "Soy Amelia Folch.)))

```
### Writing example
```
scala> SRTWriter.SRT2CSV("input.srt", "output.csv")
```

### Background story
In `Scandal` (a TV series), `wine` was mentioned several times and I was curious to know the number of times the word was used in the entire series (from season 1 - 7).
