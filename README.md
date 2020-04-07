# Sous-title

`SRT` to `CSV` converter

Read and convert `.srt` file to `csv`


```scala
import com.sous.title.core.SRTReader // for reading file
import com.sous.title.core.SRTWriter.SRT2CSV // for reading and writing to csv
```
### Reading example
```
scala> val reader = new SRTReader().open("file.srt")
reader: List(SRT(1,00:00:33.599 --> 00:00:35.270,List((NARRA) "Soy Amelia Folch.)))

```
### Inline reader example
Inline reader returns a list containing each segment of the `.srt` file

```
scala> val srt =
      """1
        |00:00:33,599 --> 00:00:35,270
        |(NARRA) "Soy Amelia Folch.
        |
        |2
        |00:00:36,199 --> 00:00:39,870
        |Tengo 23 años y sin embargo
        |he salvado la vida del Empecinado.
        |
        |3
        |00:00:45,160 --> 00:00:46,550
        |(Disparo)""".stripMargin
```

```scala>
scala> val inlineReader = new SRTReader().reader(srt)
```

### Writing example
```
scala> SRT2CSV("input.srt", "output.csv")
```

### Motivation
In `Scandal` (a TV series), `wine` was mentioned several times and I was curious to know the number of times the word was used in the entire series (from seasons 1 - 7).

### License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
