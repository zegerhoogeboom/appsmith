import { ReduxActionTypes, ReduxAction } from "constants/ReduxActionConstants";
import { BatchAction, batchAction } from "actions/batchActions";

export interface UpdateWidgetMetaPropertyPayload {
  widgetId: string;
  updates: Record<string, unknown>;
}
export const updateWidgetMetaProperty = (
  widgetId: string,
  updates: Record<string, unknown>,
): BatchAction<UpdateWidgetMetaPropertyPayload> => {
  return batchAction({
    type: ReduxActionTypes.SET_META_PROP,
    payload: {
      widgetId,
      updates,
    },
  });
};

export const resetWidgetMetaProperty = (
  widgetId: string,
): BatchAction<{ widgetId: string }> => {
  return batchAction({
    type: ReduxActionTypes.RESET_WIDGET_META,
    payload: {
      widgetId,
    },
  });
};

export const resetChildrenMetaProperty = (
  widgetId: string,
): ReduxAction<{ widgetId: string }> => {
  return {
    type: ReduxActionTypes.RESET_CHILDREN_WIDGET_META,
    payload: {
      widgetId,
    },
  };
};
