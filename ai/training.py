#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 21 13:14:56 2018

@author: lappv
"""
from sklearn.naive_bayes import GaussianNB
from common import feature_process as fp

def doTraining(X, y):
    classifier = GaussianNB()
    classifier.fit(X, y)
    #save model
    fp.saveModel(classifier)

data = fp.loadTrainingData()
doTraining(data[0], data[1])