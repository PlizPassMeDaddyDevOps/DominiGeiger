
variable "name" {
  description = "Base name for the Heroku app"
  type = "string"
}

variable "region" {
  description = "The region for Heroku deploy"
  default = "eu"
  type = "string"
}
