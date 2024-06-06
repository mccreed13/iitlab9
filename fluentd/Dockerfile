FROM fluent/fluentd:v1.17.0-debian-amd64-1.0

USER root

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
    build-essential ruby-dev sudo \
    && sudo gem install fluent-plugin-elasticsearch -v 5.4.3 \
    && sudo gem install elasticsearch -v 8.0.0 \
    && sudo gem sources --clear-all \
    && sudo apt-get remove --purge -y build-essential ruby-dev \
    && sudo apt-get autoremove -y \
    && sudo apt-get clean \
    && rm -rf /var/lib/apt/lists/* /home/fluent/.gem/ruby/2.3.0/cache/*.gem

USER fluent
