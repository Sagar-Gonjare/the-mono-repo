# Use an official Ruby image as a parent image
FROM ruby:2.7

# Install JDK and Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Install build-essential package for building native extensions
RUN apt-get install -y build-essential

# Install license_finder gem
RUN gem install license_finder

# Set working directory
WORKDIR /scan

# Command to run license_finder
ENTRYPOINT ["license_finder"]
