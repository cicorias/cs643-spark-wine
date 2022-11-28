# Running the model

0. Create some local path and a sub directory called `data`
1. Place the test.csv data file in the `./data/test.csv` location -- this is all that works.
2. run from a bash prompt `docker run -it --rm  -v $PWD/data:/data -v $PWD/data:/app/data  cicorias/predict:latest`
3. Capture the output for the full prediction table from the console output.
4. In the `./data/` path there will be an `"output-*.txt"` file -- this has the results
