provider "heroku" {
  version = "~> 2.0"
}

resource "heroku_app" "test" {
  name = "${var.name}-test"
  region = "${var.region}"
}

resource "heroku_app" "staging" {
  name = "${var.name}-staging"
  region = "${var.region}"
}

resource "heroku_app" "production" {
  name = "${var.name}-production"
  region = "${var.region}"
}

resource "heroku_pipeline" "deploy" {
  name = "${var.name}-pipeline-deploy"
}

resource "heroku_pipeline_coupling" "staging" {
  app = "${heroku_app.staging.name}"
  pipeline = "${heroku_pipeline.deploy.id}"
  stage = "staging"
}

resource "heroku_pipeline_coupling" "production" {
  app = "${heroku_app.production.name}"
  pipeline = "${heroku_pipeline.deploy.id}"
  stage = "production"
}
