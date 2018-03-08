# loggit-maven-plugin ![icon](/src/site/resources/images/icon_64.png)

[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![codecov](https://codecov.io/gh/llorllale/loggit-maven-plugin/branch/master/graph/badge.svg)](https://codecov.io/gh/llorllale/loggit-maven-plugin)
[![Build Status](https://travis-ci.org/llorllale/loggit-maven-plugin.svg?branch=master)](https://travis-ci.org/llorllale/loggit-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.llorllale/loggit-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.llorllale/loggit-maven-plugin)
[![PDD status](http://www.0pdd.com/svg?name=llorllale/loggit-maven-plugin)](http://www.0pdd.com/p?name=llorllale/loggit-maven-plugin)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://llorllale.github.io/loggit-maven-plugin/license.html)

## Why another changelog plugin?

None of the other changelog plugins have the right *combination* of features that I need:
* Markdown format
* Smarter selection of commit ranges (including tags)
* Project activity

## Features

* Transform the git log into any format using [XSLT 3.0](https://www.w3.org/TR/xslt-30/).
* Specify start and end tags to create a changelog that shows a slice of your git log
* Filter commits based on regular expressions
* Specify all configurations via the command line or `settings.xml`

## Non goals

* Performance: this plugin does not aim to be the fastest nor use the least resources nor have the smallest footprint

## Usage

Include the plugin in your pom. All configurations have default values and are optional (values shown below are default).
They are also available from the command line if you prefix them with `loggit.` (eg. `-Dloggit.maxEntries`).

```xml
  <build>
    <plugins>
      ...
      <plugin>
        <groupId>org.llorllale</groupId>
        <artifactId>loggit-maven-plugin</artifactId>
        <version>${pluginVersion}</version>
        <configuration>
          <repo>${basedir}</repo>
          <outputFile>${project.build.directory}/gitlog.xml</outputFile>
          <format>default</format>
          <customFileFormat></customFileFormat> <!-- empty -->
          <branch>master</branch>
          <maxEntries>2147483647</maxEntries> <!-- Integer.MAX_VALUE -->
          <startTag></startTag> <!-- empty -->
          <endTag></endTag> <!-- empty -->
          <includeRegex>.*</includeRegex>
        </configuration>
      </plugin>
      ...
    <plugins>
  </build>
```

### Configuration

* `<repo>`: path to the root directory of the git repo
* `<outputFile>`: path to the file where the changelog should be written to
* `<format>`: desired output format (see relevant example below). Possible values are `default`, `markdown`, and `custom`
* `<customFileFormat>`: path to the custom format file (used only when `<format>` is `custom` (see relevant example below))
* `<branch>`: the git branch from which to read the changelog
* `<maxEntries>`: the maximum number of entries to read into the changelog
* `<startTag>`: if specified, will truncate the log starting at the commit with the given tag
* `<endTag>`: if specified, will exclude all commits that appear *before* a commit with the given tag
* `<includeRegex>`: includes only commits with messages that match the given regular expression

## How it works

*Loggit* is powered by [jGit](https://www.eclipse.org/jgit/), [XSLT 3.0](https://www.w3.org/TR/xslt-30/), [jcabi-xml](https://github.com/jcabi/jcabi-xml), and [saxon-HE](http://saxon.sourceforge.net/)). After reading the gitlog and creating an XML in compliance with [this schema](src/main/resources/xsd/schema.xsd), everything else is done using XSLT. **XSLT is an extremely powerful tool that lets you - the user - transform the git log into any textual format imaginable.**

In three stages:

1. The git log is read and the XML is built (relevant configs: `<repo>`, `<branch>`)
2. The XML is pre-processed for common use cases (relevant configs: `<maxEntries>`, `<startTag>`, `<endTag>`, `<includeRegex>`)
3. The XML is post-processed using XSLT and the result is written to file (relevant configs: `<format>`, `<customFileFormat>`, `<outputFile>`)

## Examples

### Default markdown format

The markdown transformation [bundled](src/main/resources/xsl/post/markdown.xsl) with *loggit* is very primitive and basic.
It is not really intended for much use outside of demonstration purposes. Assuming you want to use it:

```xml
      <plugin>
        <groupId>org.llorllale</groupId>
        <artifactId>loggit-maven-plugin</artifactId>
        <version>${pluginVersion}</version>
        <configuration>
          <format>markdown</format>
        </configuration>
      </plugin>
```

Then the output will look similar to this:

```
# CHANGELOG

* id: 9d45cab (by John Doe)
      Short message
* id: 177357d (by Smith Jones)
      Fixed typo.
* id: 3e64aa1 (by Alicia Banks)
      Initial commit.
```

### Using a custom format

Use the following plugin configuration:

```xml
      <plugin>
        <groupId>org.llorllale</groupId>
        <artifactId>loggit-maven-plugin</artifactId>
        <version>${pluginVersion}</version>
        <configuration>
          <format>custom</format>
          <customFileFormat>path/to/custom/xslt/file.xsl</customFileFormat>
        </configuration>
      </plugin>
```

`path/to/custom/xslt/file.xsl` is the file where you'll have your custom XSLT 3.0 transformation that can output to any format you want.

## Feedback
Please direct any questions, feature requests or bugs to the [issue tracker](https://github.com/llorllale/loggit-maven-plugin/issues/).

## How to contribute?
See [CONTRIBUTING](./CONTRIBUTING.md).

## License
`loggit-maven-plugin` is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0). A copy of the license has been included in [LICENSE](./LICENSE).

<br/>

<div>Icon made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC BY 3.0</a></div>

