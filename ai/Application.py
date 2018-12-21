#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Oct  2 18:22:13 2018

@author: lappv
"""

from flask import Flask, request, jsonify
from common import feature_process as fp
import tensorflow as tf

app = Flask(__name__)


def init():
    app.config.from_object('config')
    loadModel()

def loadModel():
    global featureMethod, model, graph
    graph = tf.get_default_graph()
    featureMethod = fp.loadFeatureMethod()
    model = fp.loadModel()
    print('OK')


@app.route("/sentiment", methods=["GET", "POST"])
def predict():
    '''API get sentiment
    '''
    # get json post data
    postData = request.get_json()

    # get api params
    if (postData != None):
        comment = postData['comment']
    else:
        comment = request.args.get('comment')

    # log
    app.logger.info(comment)

    # predict
    with graph.as_default():
        # gen feature
        feature = fp.genFeature(comment, featureMethod)
        label = model.predict(feature)
        print(label)

    # return response by json
    return jsonify({'label': int(label[0])})

if __name__ == "__main__":
    init()
    app.run(debug=True, host='0.0.0.0', port=5555)
