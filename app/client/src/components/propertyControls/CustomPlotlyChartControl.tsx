import React from "react";
import InputTextControl, { InputText } from "./InputTextControl";

class CustomPlotlyChartControl extends InputTextControl {
  render() {
    const expected = "{\n  layout: {},\n  data: []\n}";
    const {
      propertyValue,
      isValid,
      label,
      placeholderText,
      dataTreePath,
      validationMessage,
    } = this.props;
    return (
      <InputText
        dataTreePath={dataTreePath}
        errorMessage={validationMessage}
        expected={expected}
        isValid={isValid}
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
