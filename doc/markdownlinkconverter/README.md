# Markdown Link Converter

The Markdown link Converter, which filters markdown pages before the processing
of doxygen, converts the links in markdown pages. It is set up as input filter
in doxygen, if a markdown file is desired to be in the API documentation
it only must have the extension `.md` and be in the `INPUT` path.

The Markdown link Converter gives each markdown file a header `{ #header }` which is attached to a title
and converts the links to refer to this headers. This conversion
happens in 2 passes, which is needed because there can be files with no title.

## Usage for manual invocation

	markdownlinkconverter [<cmake-cache-file>] <input-file>

**The <input-file> parameter must be an absolute path!**

## Conventions

* Links starting with `@ref`, `#` for anchors and `http` for extern links
  wont be touched.
* All other links to markdown or arbitrary source files will be converted.
* To refer to a folder use `/` at the end of a link. This feature was introduced
  to be compatible with github, where you can show the content of a folder in
  combination with the README.md of the containing folder. Links ending with
  `/` will be changed to `/README.md`.
* Anchors wont work in imported markdown pages.

## Link Validation

The link validation works with a simple try to `fopen` the file,
which the link refers to.

## Further improvements (which will be introduced in a later version):

* optimize pdf output (also UTF-8 encoding)
* if title contains --, this should be @brief
* add http link print
