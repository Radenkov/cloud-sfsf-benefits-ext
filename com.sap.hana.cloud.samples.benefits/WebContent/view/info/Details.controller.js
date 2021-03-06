sap.ui.controller("com.sap.hana.cloud.samples.benefits.view.info.Details", {
	onBeforeRendering : function() {
		this.loadModel();
	},

	loadModel : function() {
		if (!this.getView().getModel()) {
			this.getView().setModel(new sap.ui.model.json.JSONModel());
			this.getView().getModel().loadData("api/user/info", null, false);
		}

	},

	onNavPressed : function() {
		sap.ui.getCore().getEventBus().publish("nav", "home");
	},
});
