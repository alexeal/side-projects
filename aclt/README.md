# aclt

**aclt** stands for **A**lexeal's **C**ommand-**L**ine **T**ool. I know, I know, I've searched far and wide to find a name for my script... Anyways, I wanted to do a script to ease some commands. I'll update the script as far as I'll find some new feature to add to.

## How to use the script 

### 1) Executing the script locally
`chmod 755 aclt` will make the file executable. Then in your terminal, you juste do : `./aclt <option>`
### 2) Executing the script as a "standalone"
Execute those commands (`sudo` might be required): 
``` console
cp <aclt filepath> /usr/local/bin
cd /usr/local/bin
chmod 755 aclt 
```
Then reboot the terminal app and you'll be able to use `aclt` on your terminal **everywhere**.
## Help page
``` console
Usage: aclt [options]
Options:
--extract <archive>                             extract archive
Alternative: -e <archive>
Supported files: zip, 7zip, tar and epub
Warning: 7zip requires 7zip tools (e.g: p7zip)

--create --epub <directory>                     create an epub from the given directory
Alternative: -c -e <directory>

--create --secret-zip <file|directory>          create zip with a password
Alternative: -sz <file|directory>

--remove <filename>                             remove recursively file having given filename
Alternative: -rm <filename>
Warning: filename must be a string

--help                                          print help and exit
--version                                       print version and exit
-l                                              print license and exit
```

## Features
### 20240516

- Unpack et (re)pack epub files

- Create zip with password 

- Extract easily zip, 7zip and epub files

Though, it requires zip and 7zip to be installed beforehand.

## License

Copyright (c) 2024 - alexeal 

Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the 
“Software”), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, 
distribute, sublicense, and/or sell copies of the Software, and to 
permit persons to whom the Software is furnished to do so, subject to 
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, 
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
