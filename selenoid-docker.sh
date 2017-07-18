#!/bin/bash
cd "${0%/*}"
docker run --rm                                   \
    -v /var/run/docker.sock:/var/run/docker.sock    \
    aerokube/cm:1.0.0 selenoid                      \
    --last-versions 2                               \
    --tmpfs 128 --pull > browsers.json 


    docker run -d --name selenoid                     \
    -p 4444:4444                                    \
    -v `pwd`:/etc/selenoid:ro                       \
    -v /var/run/docker.sock:/var/run/docker.sock    \
    aerokube/selenoid   \
    -conf  -limit 10