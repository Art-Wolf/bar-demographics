module Bar
  class Stat < Grape::API
    version 'v1'
    format :json

    resource :stat do
      desc "Create stat" , params: Bar::Models::Stat.fields.dup.tap {|fields| fields.delete("_id") }
      post do
        stat = Bar::Models::Stat.create!( {
          name: params[:name],
          birthday: params[:birthday],
          gender: params[:gender],
          bartender_count: params[:bartender_count],
          patron_count: params[:patron_count],
          duration: params[:duration],
          longitude: params[:longitude],
          latitude: params[:latitude]
        } )

        # Returns the user id as a JSON escaped String
        stat.id.to_s
      end

      desc "Retrieve a stat based on the ID"
      params do
        requires :id, type: String, desc: "User ID."
      end
      get do
        stat = Bar::Models::Stat.where({id: params[:id]})
        error! "Not Found", 404 unless stat
        stat.as_json
      end

      desc "Update a stat by id.", params: Bar::Models::Stat.fields.merge( "id" => {description: "Stat id.", required: true})
      params do
        requires :id, type: String, desc: "Stat ID."
      end
      put do
        stat = Bar::Models::Stat.find_by({id: params[:id]})
        error! "Not Found", 404 unless stat
        values = {}
        values[:id] = params[:id] if params.key?(:id)
        values[:name] = params[:name] if params.key?(:name)
        values[:birthday] = params[:birthday] if params.key?(:birthday)
        values[:gender] = params[:gender] if params.key?(:gender)
        values[:bartender_count] = params[:bartender_count] if params.key?(:bartender_count)
        values[:patron_count] = params[:patron_count] if params.key?(:patron_count)
        values[:duration] = params[:duration] if params.key?(:duration)
        values[:longitude] = params[:longitude] if params.key?(:longitude)
        values[:latitude] = params[:latitude] if params.key?(:latitude)
        stat.update_attributes!(values)
        stat.as_json
      end

      desc "Delete a stat by id.", params: { "id" => {description: "Stat id.", required: true}}
      params do
        requires :id, type: String, desc: "Stat ID."
      end
      delete do
        stat = Bar::Models::Stat.find_by({id: params[:id]})
        error! "Not Found", 404 unless stat
        stat.destroy
        stat.as_json
      end
    end
  end
end
