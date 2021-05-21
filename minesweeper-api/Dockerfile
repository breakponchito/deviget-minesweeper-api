FROM openliberty/open-liberty:kernel-java8-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Alfonso Valdez" \
  org.opencontainers.image.vendor="Open Liberty" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/breakponchito/deviget-minesweeper-api" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="Open Liberty" \
  name="minesweeper-api" \
  version="$VERSION-$REVISION" \
  summary="The MineSweeper api service" \
  description="This image contains the minesweeper api running with Open Liberty runtime."

COPY --chown=1001:0 \
    src/main/liberty/config \
    /config/

COPY --chown=1001:0 \
    target/minesweeper-api.war \
    /config/apps

RUN configure.sh
