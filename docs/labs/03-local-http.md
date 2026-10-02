# Lab 3: Local HTTP

## Goal
Learn HTTP safely on localhost.

## Step 1: Create a web folder

```bash
mkdir -p ~/nethunter-labs/http
cd ~/nethunter-labs/http
echo '<h1>NetHunter Lab</h1>' > index.html
```

## Step 2: Start a local server

```bash
python -m http.server 8000 --bind 127.0.0.1
```

This serves the current folder only on localhost.

Keep that terminal running.

## Step 3: Open another terminal and inspect headers

```bash
curl -I http://127.0.0.1:8000
```

Look for:
- HTTP status
- server header
- content type
- content length

## Step 4: Fetch the page

```bash
curl http://127.0.0.1:8000
```

You should see your HTML.

## Exercise

Edit `index.html`, refresh with `curl`, and compare the response headers before and after.

Stop the server with `Ctrl+C`.
