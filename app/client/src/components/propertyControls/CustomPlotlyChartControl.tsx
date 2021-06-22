import React from "react";
import InputTextControl, { InputText } from "./InputTextControl";

class CustomPlotlyChartControl extends InputTextControl {
  render() {
    const expected = "{\n  layout: {},\n  data: []\n}";
    const { dataTreePath, label, placeholderText, propertyValue } = this.props;
    return (
      <InputText
        dataTreePath={dataTreePath}
        expected={expected}
        label={label}
        onChange={this.onTextChange}
        placeholder={placeholderText}
        value={propertyValue}
      />
    );
  }
  static getControlType() {
    return "CUSTOM_PLOTLY_CHARTS_DATA";
  }
}

export default CustomPlotlyChartControl;
