module Bar
  module Models
    class Stat
      include Mongoid::Document
      store_in collection: "stats", database: "app29704509"

      field :id, type: String, description: "Stat id."
      field :name, type: String, description: "The user's name."
      field :birthday, type: String, description: "The user's birthday."
      field :gender, type: String, description: "The user's gender."
      field :bartender_count, type: String, description: "Count of bartenders."
      field :patron_count, type: String, description: "Count of patrons."
      field :duration, type: String, description: "Duration of ordering."
      field :longitude, type: String, description: "Bar's Longitude."
      field :latitude, type: String, description: "Bar's Latitude."
    end
  end
end
